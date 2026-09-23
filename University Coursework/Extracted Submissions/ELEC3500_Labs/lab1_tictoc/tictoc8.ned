//
// This file is part of an OMNeT++/OMNEST simulation example.
//
// Copyright (C) 2003-2015 Andras Varga
//
// This file is distributed WITHOUT ANY WARRANTY. See the file
// `license' for details on this and other legal matters.
//


//simple Txc8
//{
//    parameters:
//        @display("i=block/routing");
//        volatile double delayTime @unit(s);
//    gates:
//        input in;
//        output out;
//}

simple Tic8{
    parameters:
        @display("i=block/routing");
        volatile double delayTime @unit(s);
    gates:
        input in;
        output out;

}

simple Toc8{
 parameters:
        @display("i=block/routing");
        volatile double delayTime @unit(s);
    gates:
        input in;
        output out;

}

network Tictoc8
{
    submodules:
        tic: Tic8 {
            parameters:
                @display("i=,cyan");
        }
        toc: Toc8 {
            parameters:
                @display("i=,gold");
        }
    connections:
        tic.out --> {  delay = 100ms; } --> toc.in;
        tic.in <-- {  delay = 100ms; } <-- toc.out;
}
