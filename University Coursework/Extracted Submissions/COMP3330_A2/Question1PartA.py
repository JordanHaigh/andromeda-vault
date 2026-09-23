from keras.preprocessing.image import ImageDataGenerator
from keras.models import Sequential
from keras.layers import Conv2D, MaxPooling2D
from keras.layers import Activation, Dropout, Flatten, Dense
from keras import backend as K
from keras.preprocessing import image
from keras.optimizers import Adam
import matplotlib.pyplot as plt
import numpy as np
import os

# CNN expects a fixed input image dimensions
# We will rescale all our images to 100x100
img_width, img_height = 100, 100

train_data_dir = 'data/train/Allchosen'
validation_data_dir = 'data/validation/Allchosen'



# Specify number of train and validation samples, epochs, and batch size
nb_train_samples = 4898
nb_validation_samples = 1646
epochs = 10      # We will keep epoch to 10 for quick training on Jupyterhub server, for better results use higher epoch
batch_size = 16 # The number of training examples in one forward/backward pass.
                # The higher the batch size, the more memory space you'll need.


# Configure Keras input data format

if K.image_data_format() == 'channels_first':
    input_shape = (3, img_width, img_height)
else:
    input_shape = (img_width, img_height, 3)

model = Sequential()
model.add(Conv2D(32, (3, 3), input_shape=input_shape))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Conv2D(32, (3, 3)))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Conv2D(64, (3, 3)))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Flatten())
model.add(Dense(64))
model.add(Activation('relu'))
model.add(Dropout(0.5))
model.add(Dense(10))
model.add(Activation('sigmoid'))





ADAM = Adam(lr=0.0001) #Optimiser

model.compile(loss = 'categorical_crossentropy',
              optimizer=ADAM,
              metrics=['accuracy'])
model.summary()


# This is the augmentation configuration we will use for training
train_datagen = ImageDataGenerator(
    rescale=1. / 255,
    shear_range=0.2,
    zoom_range=0.2,
    horizontal_flip=True)

test_datagen = ImageDataGenerator(rescale=1. / 255)


train_generator = train_datagen.flow_from_directory(
    train_data_dir,
    target_size=(img_width, img_height),
    batch_size=batch_size,
    class_mode='categorical')

validation_generator = test_datagen.flow_from_directory(
    validation_data_dir,
    target_size=(img_width, img_height),
    batch_size=batch_size,
    class_mode='categorical')


history = model.fit_generator(
    train_generator,
    steps_per_epoch=nb_train_samples // batch_size,
    epochs=epochs,
    validation_data=validation_generator,
    validation_steps=nb_validation_samples // batch_size,
    verbose =1)




plt.rcParams['figure.figsize'] = (16,16) # Make the figures a bit bigger
base_path = 'data/test/'
classes = {0: 'Apple', 1: 'Avocado', 2: 'Banana', 3: 'Blueberry', 4: 'Lemon', 5: 'Orange', 6: 'Pineapple',
           7: 'Pomegranate', 8: 'Raspberry', 9: 'Strawberry'}
test_files = []

for x in os.listdir(base_path):
    if(x.endswith('.jpg')):
        test_files.append(x)

for i,x in enumerate(test_files):
    print(x)
    img = image.load_img(base_path + x, target_size=(100, 100)) # Our trained network expects input images of 150x150 dimensions
    x = image.img_to_array(img)
    x = np.expand_dims(x, axis=0)
    output = model.predict(x)
    plt.subplot(5, 8, i+1)

    print(classes[int(np.argmax(output))])
    print(int(np.argmax(output)))
    plt.imshow(img)
    plt.title(" Class {}".format(classes[int(np.argmax(output))]))

    plt.axis('off')
plt.show()

print("Done")
