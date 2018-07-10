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

var createHash = function(password) {
    return bCrypt.hashSync(password, bCrypt.genSaltSync(10), null);
}

passport.use('local-signup', new LocalStrategy({
        passReqToCallback: true // allows us to pass back the entire request to the callback
    },
    function(req, email, password, done) {

        findOrCreateUser = function() {
            // find a user in Mongo with provided username
            User.findOne({
                'email': email
            }, function(err, user) {
                // In case of any error, return using the done method
                if (err) {
                    console.log('Error in SignUp: ' + err);
                    return done(err);
                }
                // already exists
                if (user) {
                    console.log('User already exists with email: ' + email);
                    return done(null, false, req.flash('message', 'User Already Exists'));
                } else {
                    // if there is no user with that email
                    // create the user
                    var newUser = new User();

                    // set the user's local credentials
                    newUser.email = email;
                    newUser.password = createHash(password);
                    newUser.typeOfUser = req.body.typeOfUser;
                    newUser.phoneNumber = req.body.phoneNumber;
                    newUser.CMND = req.body.CMND;
                    newUser.Name = req.body.Name;

                    // save the user
                    newUser.save(function(err) {
                        if (err) {
                            console.log('Error in Saving user: ' + err);
                            throw err;
                        }
                        console.log('User Registration succesful');
                        return done(null, newUser);
                    });
                }
            });
        };
        // Delay the execution of findOrCreateUser and execute the method
        // in the next tick of the event loop
        process.nextTick(findOrCreateUser);
    }));


var isValidPassword = function(user, password) {
    return bCrypt.compareSync(password, user.password);
}

passport.use('local-signin', new LocalStrategy({
        passReqToCallback: true
    },
    function(req, email, password, done) {
        // check in mongo if a user with username exists or not
        User.findOne({
                'email': email
            },
            function(err, user) {
                // In case of any error, return using the done method
                if (err)
                    return done(err);
                // Username does not exist, log error & redirect back
                if (!user) {
                    console.log('User Not Found with email ' + email);
                    return done(null, false,
                        req.flash('message', 'User Not found.'));
                }
                // User exists but wrong password, log the error 
                if (!isValidPassword(user, password)) {
                    console.log('Invalid Password');
                    return done(null, false,
                        req.flash('message', 'Invalid Password'));
                }
                // User and password both match, return user from 
                // done method which will be treated like success
                return done(null, user);
            }
        );
    }));