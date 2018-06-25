var controller = {};

var models = require('../models');

controller.getAll = function(callback) {
    models.User
        .findAll()
        .then(function(users) {
            callback(users);
        });
}

controller.getById = function(userId, callback) {
    models.User
        .findOne({
            where: {
                ID: userId
            },
        })
        .then(function(user) {
            callback(user);
        });
}