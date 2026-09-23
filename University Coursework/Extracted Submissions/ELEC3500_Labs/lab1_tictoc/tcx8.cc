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
class Tic8: public cSimpleModule {
private:
    simtime_t timeout;  // timeout
    cMessage *timeoutEvent;  // holds pointer to the timeout self-message

public:
    Tic8();
    virtual ~Tic8();

protected:
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
};

Define_Module(Tic8);

Tic8::Tic8() {
    timeoutEvent = nullptr;
}

Tic8::~Tic8() {
    // Dispose of dynamically allocated the objects
    cancelAndDelete(timeoutEvent);

}

void Tic8::initialize() {
    timeout = 1.0;
    timeoutEvent = new cMessage("timeoutEvent");

    EV << "Sending initial message \n";
    cMessage *msg = new cMessage("tictocMsg");
    send(msg, "out");
    scheduleAt(simTime() + timeout, timeoutEvent);

}

void Tic8::handleMessage(cMessage *msg) {
    if (msg == timeoutEvent) {
        //if message is timeout event, it means packet didnt arrive in time and
        //must be resent
        EV << "Timeout expired, resending message and restarting timer\n";
        cMessage *newMsg = new cMessage("tictocMsg");
        send(newMsg, "out");
        scheduleAt(simTime() + timeout, timeoutEvent);
    } else {
        //message arrived
        EV << "Message arrived. timer cancelled.\n";
        cancelEvent(timeoutEvent);
        delete msg;

        //send a new message bacl
        cMessage *newMsg = new cMessage("tictocMsg");
        send(newMsg, "out");
        scheduleAt(simTime() + timeout, timeoutEvent);
    }
}

////////////////////////////////////////////////////////////////////////////////

class Toc8: public cSimpleModule {

protected:

    virtual void handleMessage(cMessage *msg) override;
};

Define_Module(Toc8);

void Toc8::handleMessage(cMessage *msg) {
    if (uniform(0,1) < 0.1) {
        EV << "Losing message\n";
        bubble("message lost"); //makes animation more informative
    } else {
        EV << "Sending back same message as ack \n";
        send(msg, "out");
    }
}
