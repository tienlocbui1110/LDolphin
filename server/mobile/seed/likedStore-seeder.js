var LikedStore = require('../models/likedStore');

var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/lDophin');

var likedStore = [
    new LikedStore({
        imagePath: "aa/b",
        userId: "5b421b798ad2d16f20d18b57",
    }),
    new LikedStore({
        imagePath: "aa/b",
        userId: "5b421b798ad2d16f20d18b58",
    }),
];

var done = 0;

for (let i = 0; i < likedStore.length; i++) {
    likedStore[i].save(function(err, result) {
        done++;
        if (done == likedStore.length) {
            exit();
        }
    });
}

function exit() {
    mongoose.disconnect();
}