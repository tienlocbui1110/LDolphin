var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Schema = new Schema({
    ID: {type: Number, required: true},
    Name: {type: String},
    PhoneNumber : {type: String},
    IdentifycardNumber : {type: String},
    Email : {type: String},
});

model.exports = mongoose.model('UserDetail', schema);