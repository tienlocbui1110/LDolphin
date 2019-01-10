var mongoose = require('mongoose')
var passportLocalMongoose = require("passport-local-mongoose");
var Schema = mongoose.Schema

var UserSchema = new Schema({
    email: {
        type: String,
        required: true,
        unique: true
    },
    name: {
        type: String,
        required: true,
    }
});

UserSchema.plugin(passportLocalMongoose,{
    usernameField: "email"
});

module.exports = mongoose.model('User', UserSchema);