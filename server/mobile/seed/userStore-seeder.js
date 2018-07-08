var UserStore = require('../models/userStore');

var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/lDophin');

var userStore = [
    new UserStore({
        imagePath: "aa/b",
        userId: "5b421b798ad2d16f20d18b57",
    }),
    new UserStore({
        imagePath: "aa/b",
        userId: "5b421b798ad2d16f20d18b58",
    }),
];

var done = 0;

for (let i = 0; i < userStore.length; i++) {
    userStore[i].save(function(err, result) {
        done++;
        if (done == userStore.length) {
            exit();
        }
    });
}

function exit() {
    mongoose.disconnect();
}