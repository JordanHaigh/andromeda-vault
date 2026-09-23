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
class Txc12: public cSimpleModule {

protected:
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
    virtual void forwardMessage(cMessage *msg);
};

Define_Module(Txc12);

void Txc12::initialize() {
    if (getIndex() == 0) {
        // Boot the process scheduling the initial message as a self-message.
        char msgName[20];
        sprintf(msgName, "Txc-%d", getIndex());
        cMessage *msg = new cMessage(msgName);
        scheduleAt(0.0, msg);
    }
}

void Txc12::handleMessage(cMessage *msg) {
    if(getIndex() == 3){
        EV << "Message " << msg << " arrived.\n";
        delete msg;
    }
    else{
        forwardMessage(msg);
    }
}


void Txc12::forwardMessage(cMessage *msg) {
    // In this example, we just pick a random gate to send it on.
        // We draw a random number between 0 and the size of gate `out[]'.
        int n = gateSize("gate");
        int k = intuniform(0, n-1);

        EV << "Forwarding message " << msg << " on port out[" << k << "]\n";
        send(msg, "gate$o", k);
}
