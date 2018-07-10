var mongoose = require('mongoose'),
passportLocalMongoose = require("passport-local-mongoose");
var Schema = mongoose.Schema;

var UserSchema = new Schema({
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
    }
});

UserSchema.plugin(passportLocalMongoose,{
    usernameField: "email"
});

module.exports = mongoose.model('User', UserSchema);