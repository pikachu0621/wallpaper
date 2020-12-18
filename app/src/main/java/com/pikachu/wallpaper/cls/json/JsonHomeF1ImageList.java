/**
 * 用于列表的 数据加载
 */
package com.pikachu.wallpaper.cls.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class JsonHomeF1ImageList implements Serializable {


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public String getTodayStr() {
        return todayStr;
    }

    public void setTodayStr(String todayStr) {
        this.todayStr = todayStr;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String itemTime) {
        this.itemTime = itemTime;
    }

    public boolean isTimeItem() {
        return isTimeItem;
    }

    public void setTimeItem(boolean timeItem) {
        isTimeItem = timeItem;
    }
    /**
     * id : 87777
     * index : U_gVev6vaok
     * smallUrl : https://static-ali.ihansen.org/app/bg1440/U_gVev6vaok.jpg!w400
     * bigUrl : https://static-ali.ihansen.org/app/bg1440/U_gVev6vaok.jpg!o
     * raw : https://static-ali.ihansen.org/app/bg1440/U_gVev6vaok.jpg
     * likes : 357
     * downloads : 15682
     * collects : 1
     * imgTime : 2017-12-24
     * width : 4000
     * height : 6000
     * color : #D4C9F6
     * info : {"likes":357,"views":3479472,"downloads":15681,"description":"Moon in the blue sky","tags":[{"title":"purple"},{"title":"sky"},{"title":"sunset"},{"title":"dawn"},{"title":"dusk"},{"title":"nature"},{"title":"outdoors"},{"title":"red sky"},{"title":"sunrise"},{"title":"background"},{"title":"crescent moon"},{"title":"moon"},{"title":"blue"},{"title":"shape"},{"title":"night"},{"title":"sunset sky"},{"title":"starry sky"},{"title":"night time"},{"title":"horizon"},{"title":"hillside"}],"exif":{"make":"SONY","model":"ILCE-7M2","exposure_time":"1/125","aperture":"3.5","focal_length":"55.0","iso":640},"location":{"position":{"latitude":0,"longitude":0}},"user":{"id":"qC-urTD_62E","updated_at":"2020-12-11T19:19:55-05:00","username":"huguesdb","name":"Hugues de BUYER-MIMEURE","first_name":"Hugues","last_name":"de BUYER-MIMEURE","portfolio_url":"http://instagram.com/huguesdbm/","bio":"Hi, I'm a french photographer with Sony Alpha 7III based in Lyon, France.","location":"Lyon","profile_image":{"large":"https://images.unsplash.com/profile-1500389500141-d453d040335c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"},"instagram_username":"huguesdbm","total_collections":0,"total_likes":44,"total_photos":62,"links":{"html":"https://unsplash.com/@huguesdb"}},"urls":{"raw":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1","full":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=srgb&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=85","regular":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=1080","small":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=400","thumb":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=200"},"created_at":"2017-07-17T17:34:21-04:00","updated_at":"2020-12-12T11:01:55-05:00"}
     * todayStr : 2020-12-13
     */

    private Integer id;
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
    private InfoBean info;
    private String todayStr;
    @Expose(serialize = false,deserialize = false)
    private boolean isTimeItem = false;
    @Expose(serialize = false,deserialize = false)
    private String itemTime = "00-00";







    public static class InfoBean  implements Serializable {
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ExifBean getExif() {
            return exif;
        }

        public void setExif(ExifBean exif) {
            this.exif = exif;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public UrlsBean getUrls() {
            return urls;
        }

        public void setUrls(UrlsBean urls) {
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

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        /**
         * likes : 357
         * views : 3479472
         * downloads : 15681
         * description : Moon in the blue sky
         * tags : [{"title":"purple"},{"title":"sky"},{"title":"sunset"},{"title":"dawn"},{"title":"dusk"},{"title":"nature"},{"title":"outdoors"},{"title":"red sky"},{"title":"sunrise"},{"title":"background"},{"title":"crescent moon"},{"title":"moon"},{"title":"blue"},{"title":"shape"},{"title":"night"},{"title":"sunset sky"},{"title":"starry sky"},{"title":"night time"},{"title":"horizon"},{"title":"hillside"}]
         * exif : {"make":"SONY","model":"ILCE-7M2","exposure_time":"1/125","aperture":"3.5","focal_length":"55.0","iso":640}
         * location : {"position":{"latitude":0,"longitude":0}}
         * user : {"id":"qC-urTD_62E","updated_at":"2020-12-11T19:19:55-05:00","username":"huguesdb","name":"Hugues de BUYER-MIMEURE","first_name":"Hugues","last_name":"de BUYER-MIMEURE","portfolio_url":"http://instagram.com/huguesdbm/","bio":"Hi, I'm a french photographer with Sony Alpha 7III based in Lyon, France.","location":"Lyon","profile_image":{"large":"https://images.unsplash.com/profile-1500389500141-d453d040335c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"},"instagram_username":"huguesdbm","total_collections":0,"total_likes":44,"total_photos":62,"links":{"html":"https://unsplash.com/@huguesdb"}}
         * urls : {"raw":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1","full":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=srgb&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=85","regular":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=1080","small":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=400","thumb":"https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=200"}
         * created_at : 2017-07-17T17:34:21-04:00
         * updated_at : 2020-12-12T11:01:55-05:00
         */

        private Integer likes;
        private Integer views;
        private Integer downloads;
        private String description;
        private ExifBean exif;
        private LocationBean location;
        private UserBean user;
        private UrlsBean urls;
        private String created_at;
        private String updated_at;
        private List<TagsBean> tags;


        public static class ExifBean  implements Serializable {
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

            /**
             * make : SONY
             * model : ILCE-7M2
             * exposure_time : 1/125
             * aperture : 3.5
             * focal_length : 55.0
             * iso : 640
             */

            private String make;
            private String model;
            private String exposure_time;
            private String aperture;
            private String focal_length;
            private Integer iso;
        }

        public static class LocationBean  implements Serializable {
            public PositionBean getPosition() {
                return position;
            }

            public void setPosition(PositionBean position) {
                this.position = position;
            }

            /**
             * position : {"latitude":0,"longitude":0}
             */

            private PositionBean position;


            public static class PositionBean  implements Serializable {
                public float getLatitude() {
                    return latitude;
                }

                public void setLatitude(float latitude) {
                    this.latitude = latitude;
                }

                public float getLongitude() {
                    return longitude;
                }

                public void setLongitude(float longitude) {
                    this.longitude = longitude;
                }

                /**
                 * latitude : 0
                 * longitude : 0
                 */

                private float latitude;
                private float longitude;
            }
        }


        public static class UserBean  implements Serializable {
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

            public ProfileImageBean getProfile_image() {
                return profile_image;
            }

            public void setProfile_image(ProfileImageBean profile_image) {
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

            public LinksBean getLinks() {
                return links;
            }

            public void setLinks(LinksBean links) {
                this.links = links;
            }

            /**
             * id : qC-urTD_62E
             * updated_at : 2020-12-11T19:19:55-05:00
             * username : huguesdb
             * name : Hugues de BUYER-MIMEURE
             * first_name : Hugues
             * last_name : de BUYER-MIMEURE
             * portfolio_url : http://instagram.com/huguesdbm/
             * bio : Hi, I'm a french photographer with Sony Alpha 7III based in Lyon, France.
             * location : Lyon
             * profile_image : {"large":"https://images.unsplash.com/profile-1500389500141-d453d040335c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"}
             * instagram_username : huguesdbm
             * total_collections : 0
             * total_likes : 44
             * total_photos : 62
             * links : {"html":"https://unsplash.com/@huguesdb"}
             */

            private String id;
            private String updated_at;
            private String username;
            private String name;
            private String first_name;
            private String last_name;
            private String portfolio_url;
            private String bio;
            private String location;
            private ProfileImageBean profile_image;
            private String instagram_username;
            private Integer total_collections;
            private Integer total_likes;
            private Integer total_photos;
            private LinksBean links;

            public static class ProfileImageBean  implements Serializable {
                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                /**
                 * large : https://images.unsplash.com/profile-1500389500141-d453d040335c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128
                 */

                private String large;
            }


            public static class LinksBean  implements Serializable {
                public String getHtml() {
                    return html;
                }

                public void setHtml(String html) {
                    this.html = html;
                }

                /**
                 * html : https://unsplash.com/@huguesdb
                 */

                private String html;
            }
        }


        public static class UrlsBean  implements Serializable {
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

            /**
             * raw : https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1
             * full : https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=srgb&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=85
             * regular : https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=1080
             * small : https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=400
             * thumb : https://images.unsplash.com/photo-1500327324249-e434c1bfdfab?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxMjA3fDB8MXxhbGx8fHx8fHx8fA&ixlib=rb-1.2.1&q=80&w=200
             */

            private String raw;
            private String full;
            private String regular;
            private String small;
            private String thumb;
        }


        public static class TagsBean  implements Serializable {
            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            /**
             * title : purple
             */

            private String title;
        }
    }
}
