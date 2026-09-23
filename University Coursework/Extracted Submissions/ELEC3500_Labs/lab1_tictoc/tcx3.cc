
#include <string.h>
#include <omnetpp.h>

using namespace omnetpp;

/**
 * Derive the Txc1 class from cSimpleModule. In the Tictoc1 network,
 * both the `tic' and `toc' modules are Txc1 objects, created by OMNeT++
 * at the beginning of the simulation.
 */
class Txc3: public cSimpleModule {
private:
    int counter;
protected:
    // The following redefined virtual function holds the algorithm.
    virtual void initialize() override;
    virtual void handleMessage(cMessage *msg) override;
};

// The module class needs to be registered with OMNeT++
Define_Module(Txc3);

void Txc3::initialize() {
    // Initialize is called at the beginning of the simulation.
    // To bootstrap the tic-toc-tic-toc process, one of the modules needs
    // to send the first message. Let this be `tic'.
    counter = 3;

    // The WATCH() statement below will let you examine the variable under
       // Tkenv. After doing a few steps in the simulation, double-click either
       // `tic' or `toc', select the Contents tab in the dialog that pops up,
       // and you'll find "counter" in the list.
    WATCH(counter);

    // Am I Tic or Toc?
    if (strcmp("tic", getName()) == 0) {
        // create and send first message on gate "out". "tictocMsg" is an
        // arbitrary string which will be the name of the message object.
        cMessage *msg = new cMessage("tictocMsg");
        EV << "Sending initial message\n";
        send(msg, "out");
    }
}

void Txc3::handleMessage(cMessage *msg) {
    // The handleMessage() method is called whenever a message arrives
    // at the module. Here, we just send it to the other module, through
    // gate `out'. Because both `tic' and `toc' does the same, the message
    // will bounce between the two.

    counter--;
    if (counter == 0) {
            // If counter is zero, delete message. If you run the model, you'll
            // find that the simulation will stop at this point with the message
            // "no more events".
            EV << getName() << "'s counter reached zero, deleting message\n";
            delete msg;
        }
        else {
            EV << getName() << "'s counter is " << counter << ", sending back message\n";
            send(msg, "out");
        }
}
