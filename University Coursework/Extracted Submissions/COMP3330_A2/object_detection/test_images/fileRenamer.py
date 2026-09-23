import os
i = 1;

for filename in os.listdir("."):
    #start = "Image("
    #end = ")"
    #newName = start + str(i) + end
    if(filename != "fileRenamer.py"):
        print(str(i))
        os.rename(filename, "image (" + str(i) + ").jpg")
        i+=1
