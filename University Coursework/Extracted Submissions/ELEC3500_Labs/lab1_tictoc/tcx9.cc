// This file is part of an OMNeT++/OMNEST simulation example.
//
// Copyright (C) 2003-2015 Andras Varga
//
// This file is distributed WITHOUT ANY WARRANTY. See the file
// `license' for details on this and other legal matters.
//

#include <stdio.h>
#include <string.h>
#include <omnetpp.h>

using namespace omnetpp;

/**
 * Let us take a step back, and remove random delaying from the code.
 * We'll leave in, however, losing the packet with a small probability.
 * And, we'll we do something very common in telecommunication networks:
 * if the packet doesn't arrive within a certain period, we'll assume it
 * was lost and create another one. The timeout will be handled using
 * (what else?) a self-message.
 */
class Tic9: public cSimpleModule {

public:
    Tic9();
    virtual ~Tic9();

private:
    simtime_t timeout;  // timeout
    cMessage *timeoutEvent;  // holds pointer to the timeout self-message
    int seq;  // message sequence number
    cMessage *message;  // message that has to be re-sent on timeout

protected:
    virtual cMessage* generateNewMessage();
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
    virtual void sendCopyOf(cMessage *msg);
};

Define_Module(Tic9);

Tic9::Tic9() {
    timeoutEvent = message = nullptr;
}

Tic9::~Tic9() {
    // Dispose of dynamically allocated the objects
    cancelAndDelete(timeoutEvent);
    delete message;

}

void Tic9::initialize() {
    timeout = 1.0;
    seq = 0;
    timeoutEvent = new cMessage("timeoutEvent");

    EV << "Sending initial message \n";
    message = generateNewMessage();
    sendCopyOf(message);
    scheduleAt(simTime() + timeout, timeoutEvent);

}



void Tic9::handleMessage(cMessage *msg) {
    if (msg == timeoutEvent) {
        //if message is timeout event, it means packet didnt arrive in time and
        //must be resent
        EV << "Timeout expired, resending message and restarting timer\n";
        sendCopyOf(msg);
        scheduleAt(simTime() + timeout, timeoutEvent);
    } else {
        //message arrived
        EV << "received" << msg->getName() << "\n";

        //can also delete stored message and cancel timeout
        cancelEvent(timeoutEvent);
        delete message;

        //send another message
        message = generateNewMessage();
        sendCopyOf(message);
        scheduleAt(simTime() + timeout, timeoutEvent);
    }
}


cMessage *Tic9::generateNewMessage() {
    char msgName[20];
    sprintf(msgName, "tic-%d", ++seq);
    cMessage *msg = new cMessage(msgName);
    return msg;

}

void Tic9::sendCopyOf(cMessage *msg) {
    cMessage *copy = (cMessage*) msg->dup();
    send(copy, "out");
}
////////////////////////////////////////////////////////////////////////////////

class Toc9: public cSimpleModule {

protected:

    virtual void handleMessage(cMessage *msg) override;
};

Define_Module(Toc9);

void Toc9::handleMessage(cMessage *msg) {
    if (uniform(0, 1) < 0.1) {
        EV << "Losing message\n";
        bubble("message lost"); //makes animation more informative
    } else {
        EV << msg << "received, Sending back ack \n";
        delete msg;
        send(new cMessage("ack"), "out");
    }
}
