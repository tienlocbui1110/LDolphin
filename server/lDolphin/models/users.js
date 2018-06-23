var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Schema = new Schema({
    ID: {type: Number, required: true},
    IDDetail: {type: Number, required: true, ref: 'UserDetail'},
    IDType : {type: Number, required: true, ref: 'UserType'}
});

model.exports = mongoose.model('User', schema);