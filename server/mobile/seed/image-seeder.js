var Image = require('../models/image');

var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/lDophin');

var image = [
    new Image({
        imageLargerPath: 'abc/a',
        imageThumbnailPath: 'bcd/a',
        title: 'hinh 1',
        tag: 'man',
        Size: '223x499',
        countLike: 0,
        countComment: 0,
        shortLink: 'a/b',
        typeOfImage: 1,
        imageStatus: 1,
        userId: "5b421b798ad2d16f20d18b57",
    }),
    new Image({
        imageLargerPath: 'abc/a',
        imageThumbnailPath: 'bcd/a',
        title: 'hinh 2',
        tag: 'man',
        Size: '223x499',
        countLike: 0,
        countComment: 0,
        shortLink: 'a/b',
        typeOfImage: 1,
        imageStatus: 1,
        userId: "5b421b798ad2d16f20d18b58",
    }),
];

var done = 0;

for (let i = 0; i < image.length; i++) {
    image[i].save(function(err, result) {
        done++;
        if (done == image.length) {
            exit();
        }
    });
}

function exit() {
    mongoose.disconnect();
}