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

// Include a generated file: the header file created from tictoc14.msg.
// It contains the definition of the TictocMsg10 class, derived from
// cMessage.
#include "tictoc14_m.h"

/**
 * In this step the destination address is no longer node 2 -- we draw a
 * random destination, and we'll add the destination address to the message.
 *
 * The best way is to subclass cMessage and add destination as a data member.
 * Hand-coding the message class is usually tiresome because it contains
 * a lot of boilerplate code, so we let OMNeT++ generate the class for us.
 * The message class specification is in tictoc14.msg -- tictoc14_m.h
 * and .cc will be generated from this file automatically.
 *
 * To make the model execute longer, after a message arrives to its destination
 * the destination node will generate another message with a random destination
 * address, and so forth.
 */
class Txc14: public cSimpleModule {
private:
    long numSent;
    long numReceived;

protected:
    virtual TicTocMsg14 *generateMessage();
    virtual void forwardMessage(TicTocMsg14 *msg);
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
    virtual void refreshDisplay() const override;
};

Define_Module(Txc14);

void Txc14::initialize() {
    numSent = 0;
    numReceived = 0;
    WATCH(numSent);
    WATCH(numReceived);

    // Module 0 sends the first message
    if (getIndex() == 0) {
        // Boot the process scheduling the initial message as a self-message.
        TicTocMsg14 *msg = generateMessage();
        numSent++;
        scheduleAt(0.0, msg);
    }
}

void Txc14::handleMessage(cMessage *msg) {
    TicTocMsg14 *ttmsg = check_and_cast<TicTocMsg14 *>(msg);

    if (ttmsg->getDestination() == getIndex()) {
        // Message arrived.
        EV << "Message " << ttmsg << " arrived after " << ttmsg->getHopCount()
                  << " hops.\n";
        bubble("ARRIVED, starting new one!");
        delete ttmsg;
        numReceived++;

        // Generate another one.
        EV << "Generating another message: ";
        TicTocMsg14 *newmsg = generateMessage();
        EV << newmsg << endl;

        forwardMessage(newmsg);
        numSent++;
    } else {
        // We need to forward the message.
        forwardMessage(ttmsg);
    }
}

TicTocMsg14 *Txc14::generateMessage() {
    // Produce source and destination addresses.
    int src = getIndex();  // our module index
    int n = getVectorSize();  // module vector size
    int dest = intuniform(0, n - 2);
    if (dest >= src)
        dest++;

    char msgname[20];
    sprintf(msgname, "tic-%d-to-%d", src, dest);

    // Create message object and set source and destination field.
    TicTocMsg14 *msg = new TicTocMsg14(msgname);
    msg->setSource(src);
    msg->setDestination(dest);
    return msg;
}

void Txc14::forwardMessage(TicTocMsg14 *msg) {
    // Increment hop count.
    msg->setHopCount(msg->getHopCount() + 1);

    // Same routing as before: random gate.
    int n = gateSize("gate");
    int k = intuniform(0, n - 1);

    EV << "Forwarding message " << msg << " on gate[" << k << "]\n";
    send(msg, "gate$o", k);
}

void Txc14::refreshDisplay() const {
    char buf[40];
    sprintf(buf, "rcvd: %ld sent: %ld", numReceived, numSent);
    getDisplayString().setTagArg("t", 0, buf);

}

