# -*- coding: utf-8 -*-
"""
Created on Fri Jun  1 11:51:55 2018

@author: Jordan
"""

# pip install Pillow if you don't already have it

# import image utilities
from PIL import Image
import PIL as pil
# import os utilities
import os

# define a function that rotates images in the current directory
# given the rotation in degrees as a parameter
#def flipme():
#  # for each image in the current directory
#  for image in os.listdir(os.getcwd()):
#    # open the image
#    img = Image.open(image)
#    img = Image.transpose(Image.FLIP_LEFT_RIGHT)
#
#
#    img = Image.transpose(Image.FLIP_LEFT_RIGHT).save(image)
#
#    # rotate and save the image with the same filename
#    #img.rotate(rotationAmt).save(image)
#    # close the image
#    img.close()
#    
## examples of use
#    
#flipme()





def flip_image(image_path, saved_location):
    """
    Flip or mirror the image
    @param image_path: The path to the image to edit
    @param saved_location: Path to save the cropped image
    """
    image_obj = Image.open(image_path)
    rotated_image = image_obj.transpose(Image.FLIP_LEFT_RIGHT)
    rotated_image.save(saved_location)
    #rotated_image.show()
    
    
if __name__ == '__main__':
    for image in os.listdir(os.getcwd()):
        flip_image(image, image)
    #image = 'mantis.png'
    #lip_image(image, 'flipped_mantis.jpg')