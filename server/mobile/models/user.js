var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var schema = new Schema({
    Name: {
        type: String,
        required: true
    },
    phoneNumber: {
        type: String
    },
    CMND: {
        type: String
    },
    email: {
        type: String
    },
    password: {
        type: String,
        required: true
    },
    typeOfUser: {
        type: Number,
        required: true
    },
});

module.exports = mongoose.model('User', schema);