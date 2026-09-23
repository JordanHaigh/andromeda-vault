import xml.etree.ElementTree as ET
import pandas as pd


df_cols = ['xmin','ymin','xmax','ymax']
df = pd.DataFrame(columns=df_cols)
dfindex = 0;

dfpaths = pd.DataFrame(columns=['filepath'])

for x in range(1,181):
    tree = ET.parse("Picture ("+str(x)+").xml")
    root = tree.getroot()
    
    path = root.find('path').text
    
    #print(path)
    
            
    for child in root.findall('object/bndbox'):
        #print(child.tag, child.attrib)
        xmin = child.find('xmin').text
        ymin = child.find('ymin').text
        xmax = child.find('xmax').text
        ymax = child.find('ymax').text
        df.loc[dfindex] = [xmin,ymin,xmax,ymax]
        #df.append(path, xmin, ymin,xmax,ymax)
        print(xmin, ymin, xmax, ymax)
        
        dfpaths.loc[dfindex]=[path]
        
        dfindex += 1
    
    
df.to_csv("points.csv", sep=',', encoding='utf-8')
df.to_csv(r'boxes.txt', header=None, index=None, sep=',', mode='a')
