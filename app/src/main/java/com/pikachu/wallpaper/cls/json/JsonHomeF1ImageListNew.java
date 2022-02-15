package com.pikachu.wallpaper.cls.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * @author pkpk.run
 * @project wallpaper
 * @package com.pikachu.wallpaper.cls.json
 * @date 2022/2/15
 * @description 略
 */
public class JsonHomeF1ImageListNew implements Serializable {


    private Integer id;
    private Integer type;
    private String index;
    private String smallUrl;
    private String bigUrl;
    private String raw;
    private Integer likes;
    private Integer downloads;
    private Integer collects;
    private String imgTime;
    private Integer width;
    private Integer height;
    private String color;
    private JsonHomeF1ImageList.InfoBean info;
    @Expose(serialize = false, deserialize = false)
    private boolean isTimeItem = false;
    @Expose(serialize = false, deserialize = false)
    private String itemTime = "00-00";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getBigUrl() {
        return bigUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getCollects() {
        return collects;
    }

    public void setCollects(Integer collects) {
        this.collects = collects;
    }

    public String getImgTime() {
        return imgTime;
    }

    public void setImgTime(String imgTime) {
        this.imgTime = imgTime;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public JsonHomeF1ImageList.InfoBean getInfo() {
        return info;
    }

    public void setInfo(JsonHomeF1ImageList.InfoBean info) {
        this.info = info;
    }

    public boolean isTimeItem() {
        return isTimeItem;
    }

    public void setTimeItem(boolean timeItem) {
        isTimeItem = timeItem;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String itemTime) {
        this.itemTime = itemTime;
    }

    public static class InfoBean implements Serializable {
        private Integer likes;
        private Integer views;
        private Integer downloads;
        private String title;
        private String description;
        private List<JsonHomeF1ImageList.InfoBean.TagsBean> tags;
        private JsonHomeF1ImageList.InfoBean.ExifBean exif;
        private JsonHomeF1ImageList.InfoBean.LocationBean location;
        private JsonHomeF1ImageList.InfoBean.UserBean user;
        private JsonHomeF1ImageList.InfoBean.UrlsBean urls;
        private String created_at;
        private String updated_at;
        private String todayStr;

        public Integer getLikes() {
            return likes;
        }

        public void setLikes(Integer likes) {
            this.likes = likes;
        }

        public Integer getViews() {
            return views;
        }

        public void setViews(Integer views) {
            this.views = views;
        }

        public Integer getDownloads() {
            return downloads;
        }

        public void setDownloads(Integer downloads) {
            this.downloads = downloads;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<JsonHomeF1ImageList.InfoBean.TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<JsonHomeF1ImageList.InfoBean.TagsBean> tags) {
            this.tags = tags;
        }

        public JsonHomeF1ImageList.InfoBean.ExifBean getExif() {
            return exif;
        }

        public void setExif(JsonHomeF1ImageList.InfoBean.ExifBean exif) {
            this.exif = exif;
        }

        public JsonHomeF1ImageList.InfoBean.LocationBean getLocation() {
            return location;
        }

        public void setLocation(JsonHomeF1ImageList.InfoBean.LocationBean location) {
            this.location = location;
        }

        public JsonHomeF1ImageList.InfoBean.UserBean getUser() {
            return user;
        }

        public void setUser(JsonHomeF1ImageList.InfoBean.UserBean user) {
            this.user = user;
        }

        public JsonHomeF1ImageList.InfoBean.UrlsBean getUrls() {
            return urls;
        }

        public void setUrls(JsonHomeF1ImageList.InfoBean.UrlsBean urls) {
            this.urls = urls;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getTodayStr() {
            return todayStr;
        }

        public void setTodayStr(String todayStr) {
            this.todayStr = todayStr;
        }

        public static class ExifBean implements Serializable {
            private String make;
            private String model;
            private String exposure_time;
            private String aperture;
            private String focal_length;
            private Integer iso;

            public String getMake() {
                return make;
            }

            public void setMake(String make) {
                this.make = make;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getExposure_time() {
                return exposure_time;
            }

            public void setExposure_time(String exposure_time) {
                this.exposure_time = exposure_time;
            }

            public String getAperture() {
                return aperture;
            }

            public void setAperture(String aperture) {
                this.aperture = aperture;
            }

            public String getFocal_length() {
                return focal_length;
            }

            public void setFocal_length(String focal_length) {
                this.focal_length = focal_length;
            }

            public Integer getIso() {
                return iso;
            }

            public void setIso(Integer iso) {
                this.iso = iso;
            }
        }

        public static class LocationBean implements Serializable{
            private String title;
            private String name;
            private String country;
            private JsonHomeF1ImageList.InfoBean.LocationBean.PositionBean position;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public JsonHomeF1ImageList.InfoBean.LocationBean.PositionBean getPosition() {
                return position;
            }

            public void setPosition(JsonHomeF1ImageList.InfoBean.LocationBean.PositionBean position) {
                this.position = position;
            }

            public static class PositionBean implements Serializable{
                private Integer latitude;
                private Integer longitude;

                public Integer getLatitude() {
                    return latitude;
                }

                public void setLatitude(Integer latitude) {
                    this.latitude = latitude;
                }

                public Integer getLongitude() {
                    return longitude;
                }

                public void setLongitude(Integer longitude) {
                    this.longitude = longitude;
                }
            }
        }

        public static class UserBean implements Serializable{
            private String id;
            private String updated_at;
            private String username;
            private String name;
            private String first_name;
            private String last_name;
            private String twitter_username;
            private String portfolio_url;
            private String bio;
            private String location;
            private JsonHomeF1ImageList.InfoBean.UserBean.ProfileImageBean profile_image;
            private String instagram_username;
            private Integer total_collections;
            private Integer total_likes;
            private Integer total_photos;
            private JsonHomeF1ImageList.InfoBean.UserBean.LinksBean links;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getTwitter_username() {
                return twitter_username;
            }

            public void setTwitter_username(String twitter_username) {
                this.twitter_username = twitter_username;
            }

            public String getPortfolio_url() {
                return portfolio_url;
            }

            public void setPortfolio_url(String portfolio_url) {
                this.portfolio_url = portfolio_url;
            }

            public String getBio() {
                return bio;
            }

            public void setBio(String bio) {
                this.bio = bio;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public JsonHomeF1ImageList.InfoBean.UserBean.ProfileImageBean getProfile_image() {
                return profile_image;
            }

            public void setProfile_image(JsonHomeF1ImageList.InfoBean.UserBean.ProfileImageBean profile_image) {
                this.profile_image = profile_image;
            }

            public String getInstagram_username() {
                return instagram_username;
            }

            public void setInstagram_username(String instagram_username) {
                this.instagram_username = instagram_username;
            }

            public Integer getTotal_collections() {
                return total_collections;
            }

            public void setTotal_collections(Integer total_collections) {
                this.total_collections = total_collections;
            }

            public Integer getTotal_likes() {
                return total_likes;
            }

            public void setTotal_likes(Integer total_likes) {
                this.total_likes = total_likes;
            }

            public Integer getTotal_photos() {
                return total_photos;
            }

            public void setTotal_photos(Integer total_photos) {
                this.total_photos = total_photos;
            }

            public JsonHomeF1ImageList.InfoBean.UserBean.LinksBean getLinks() {
                return links;
            }

            public void setLinks(JsonHomeF1ImageList.InfoBean.UserBean.LinksBean links) {
                this.links = links;
            }

            public static class ProfileImageBean implements Serializable {
                private String large;

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }
            }

            public static class LinksBean implements Serializable {
                private String html;

                public String getHtml() {
                    return html;
                }

                public void setHtml(String html) {
                    this.html = html;
                }
            }
        }

        public static class UrlsBean implements Serializable {
            private String raw;
            private String full;
            private String regular;
            private String small;
            private String thumb;

            public String getRaw() {
                return raw;
            }

            public void setRaw(String raw) {
                this.raw = raw;
            }

            public String getFull() {
                return full;
            }

            public void setFull(String full) {
                this.full = full;
            }

            public String getRegular() {
                return regular;
            }

            public void setRegular(String regular) {
                this.regular = regular;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class TagsBean implements Serializable {
            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
