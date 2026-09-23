//
// This file is part of an OMNeT++/OMNEST simulation example.
//
// Copyright (C) 2003 Ahmet Sekercioglu
// Copyright (C) 2003-2015 Andras Varga
//
// This file is distributed WITHOUT ANY WARRANTY. See the file
// `license' for details on this and other legal matters.
//

#include <stdio.h>
#include <string.h>
#include <omnetpp.h>

using namespace omnetpp;

// Include a generated file: the header file created from tictoc13.msg.
// It contains the definition of the TictocMsg10 class, derived from
// cMessage.
#include "tictoc13_m.h"

/**
 * In this step the destination address is no longer node 2 -- we draw a
 * random destination, and we'll add the destination address to the message.
 *
 * The best way is to subclass cMessage and add destination as a data member.
 * Hand-coding the message class is usually tiresome because it contains
 * a lot of boilerplate code, so we let OMNeT++ generate the class for us.
 * The message class specification is in tictoc13.msg -- tictoc13_m.h
 * and .cc will be generated from this file automatically.
 *
 * To make the model execute longer, after a message arrives to its destination
 * the destination node will generate another message with a random destination
 * address, and so forth.
 */
class Txc13: public cSimpleModule {
public:
    Txc13();
    ~Txc13();
protected:
    virtual TicTocMsg13 *generateMessage();
    virtual void forwardMessage(TicTocMsg13 *msg);
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
    virtual void finish() override;
    ///////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////
private:
    cMessage *periodEvent;
    simtime_t periodLength;
    int messageId;
    cLongHistogram endtoEndStats;
    cOutVector endtoEndVector;

};

Define_Module(Txc13);

Txc13::Txc13() {
    periodEvent = nullptr;
    messageId = 0;
}

Txc13::~Txc13() {
    cancelAndDelete(periodEvent);
}

void Txc13::initialize() {
    periodLength = 0.5;
    periodEvent = new cMessage("periodEvent");
    scheduleAt(simTime() + periodLength, periodEvent);
    // Module 0 sends the first message


    //set up periodic sending

}

void Txc13::handleMessage(cMessage *msg) {
    //send another message on period
    if (msg == periodEvent) {
        cancelAndDelete(periodEvent);

        periodEvent = new cMessage("periodEvent");
        EV << "Generating new message based on period. going from 0 to 5";
        TicTocMsg13 *newMessage = generateMessage();
        scheduleAt(simTime(), newMessage); //send message now
        scheduleAt(simTime() + periodLength, periodEvent);

    } else {
        TicTocMsg13 *ttmsg = check_and_cast<TicTocMsg13 *>(msg);

        if (ttmsg->getDestination() == getIndex()) {
            // Message arrived.
            EV << "Message " << ttmsg << " arrived after "
                      << ttmsg->getHopCount() << " hops.\n";
            bubble("ARRIVED!");
            delete ttmsg;

            //        // Generate another one.
            //        EV << "Generating another message: ";
            //        TicTocMsg13 *newmsg = generateMessage();
            //        EV << newmsg << endl;
            //        forwardMessage(newmsg);

            simtime_t endToEndTime = msg->getArrivalTime() - msg->getCreationTime();
            endtoEndVector.record(endToEndTime);
            endtoEndStats.collect(endToEndTime);
        } else {
            // We need to forward the message.
            forwardMessage(ttmsg);
        }
    }

}

TicTocMsg13 *Txc13::generateMessage() {
    // Produce source and destination addresses.
    int src = getIndex();  // our module index
    int n = getVectorSize();  // module vector size
    int dest = intuniform(0, n - 2);
    int a = 1;
    if (dest >= src)
        dest++;

    char msgname[25];

    sprintf(msgname, "tic-%d-to-%d, ID:%d", src, dest, ++messageId);

    // Create message object and set source and destination field.
    TicTocMsg13 *msg = new TicTocMsg13(msgname);
    msg->setSource(src);
    msg->setDestination(dest);
    return msg;
}

void Txc13::forwardMessage(TicTocMsg13 *msg) {
    // Increment hop count.
    msg->setHopCount(msg->getHopCount() + 1);

    // Same routing as before: random gate.
    int n = gateSize("gate");
    int k = intuniform(0, n - 1);

    EV << "Forwarding message " << msg << " on gate[" << k << "]\n";
    send(msg, "gate$o", k);
}

void Txc13::finish() {
    endtoEndStats.recordAs("end to end");
}
