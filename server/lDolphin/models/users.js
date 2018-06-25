var mongoose = require('mongoose');
var Schema = mongoose.Schema;
const passportLocalMongoose = require('passport-local-mongoose');

var Schema = new Schema({
    ID: {type: Number, required: true},
    IDDetail: {type: Number, required: true, ref: 'UserDetail'},
    IDType : {type: Number, required: true, ref: 'UserType'},
    Password : {type: Number, required: true}
});

Schema.plugin(passportLocalMongoose);

model.exports = mongoose.model('User', schema);