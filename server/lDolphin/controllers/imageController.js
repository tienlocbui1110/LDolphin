var controller = {};

var models = require('../models');

controller.getAll = function (callback) {
    models.Image
        .findAll()
        .then(function (images) {
            callback(images);
        });
}

controller.getById = function (imageId, callback) {
    models.Image
        .findAll({
            where: {
                ID: imageId
            },
        })
        .then(function (image) {
            callback(image);
        });
}

controller.upImage = function (info) {
    models.ImageDetail
        .build({
            IDStatus: info.status,
            LinkLargeImage: info.linkLargeImage,
            LinkThumbnailImage: info.linkThubnailImage,
            Name: info.name,
            Title: info.title,
            Tag: info.tag,
            Size: info.size,
            ShortLink: info.shortLink
        });

    models.ImageType
        .build({
            Name: info.imageType,
        });

    models.Image
        .build({
            IDUser: info.userId,
            IDImageType: models.ImageType
                .findOne({
                    where: {
                        Name: info.imageType,
                    }
                }).ID,
            IDImageDetail: mdoels.ImageDetail
                .findOne({
                    where: {
                        Name: info.name,
                        Title: info.title,
                        Tag: info.tag,
                        Size: info.size,
                        ShortLink: info.shortLink
                    }
                }).ID,
        });
}