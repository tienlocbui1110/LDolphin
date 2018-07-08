var passport = require('passport');
var User = require('../models/user');
var LocalStrategy = require('passport-local').Strategy;
var bCrypt = require('bcrypt-nodejs');

passport.serializeUser(function(user, done) {
    done(null, user._id);
});

passport.deserializeUser(function(id, done) {
    User.find({
        "_id": id
    }, function(err, user) {
        if (user) {
            done(null, user);
        } else {}
    });
});

passport.use('local-signup', new LocalStrategy({
    email: 'email',
    password: 'password',
    passReqToCallback: true
}, function(req, email, password, done) {
    req.checkBody('email', 'Invalid email login').notEmpty();
    req.checkBody('password', 'Invalid password').notEmpty();
    var errors = req.validationErrors();
    if (errors) {
        var messages = [];
        errors.forEach(function(error) {
            messages.push(error.msg);
        });
        console.log('fail')
        return done(null, false, req.flash('error', messages));
    }
    var generateHash = function(password) {
        return bCrypt.hashSync(password, bCrypt.genSaltSync(8), null);
    };
    User.findOne({
            "email": email
        }, {
            raw: true
        })
        .then(user => {
            if (user) {
                console.log('fail')
                return done(null, false, {
                    message: 'User email is already in use.'
                });
            }
            var userPassword = generateHash(password);
            var data = {
                "email": email,
                "phoneNumber": req.body.phoneNumber,
                "CMND": req.body.CMND,
                "typeOfUser": 1,
                "password": userPassword,
                "Name": req.body.name,
            };

            User.insert(data).then(function(newUser, created) {
                if (!newUser) {
                    return done(null, false);
                }
                if (newUser) {
                    console.log('success')
                    return done(null, newUser);
                }
            });
        });
}));

passport.use('local-signin', new LocalStrategy({
        // by default, local strategy uses username and password, we will override with email
        email: 'email',
        password: 'password',
        passReqToCallback: true // allows us to pass back the entire request to the callback
    },
    function(req, email, password, done) {
        var isValidPassword = function(userpass, password) {
            console.log(userpass, password);
            console.log(bCrypt.compareSync(password, userpass));
            return bCrypt.compareSync(password, userpass);
        };
        User.findOne({
                "email": email
            }, {
                raw: true
            })
            .then(user => {

                if (!user) {
                    console.log('fail')
                    return done(null, false, {
                        message: 'User email does not exist'
                    });
                }
                if (!isValidPassword(user.password, password)) {
                    console.log('fail')
                    return done(null, false, {
                        message: 'Incorrect password.'
                    });

                }
                console.log('success')
                var userinfo = user.get();
                console.log(userinfo)
                req.session.user = user;
                return done(null, userinfo);
            }).catch(function(err) {
                console.log("Error:", err);
                return done(null, false, {
                    message: 'Something went wrong with your Signin'
                });
            });

    }
));