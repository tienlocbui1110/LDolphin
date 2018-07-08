var User = require('../models/user');

var mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/lDophin');

var user = [
    new User({
        Name: "Duc",
        phoneNumber: "0931266013",
        CMND: "272602710",
        email: "datducnguyenhuu@gmail.com",
        password: "datducFIT97",
        typeOfUser: 1,
    }),
    new User({
        Name: "Loc",
        phoneNumber: "0932355971",
        CMND: "113112115",
        email: "logbui@gmail.com",
        password: "Loc112312",
        typeOfUser: 2,
    }),
    new User({
        Name: "Trinh",
        phoneNumber: "0932224971",
        CMND: "119118117",
        email: "trinhphan@gmail.com",
        password: "Trinh33551",
        typeOfUser: 1,
    }),
];

var done = 0;

for (let i = 0; i < user.length; i++) {
    user[i].save(function(err, result) {
        done++;
        if (done == user.length) {
            exit();
        }
    });
}

function exit() {
    mongoose.disconnect();
}