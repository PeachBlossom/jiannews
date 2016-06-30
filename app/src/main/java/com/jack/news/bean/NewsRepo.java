package com.jack.news.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class NewsRepo implements Serializable{


    /**
     * pagebean : {"allNum":54188,"allPages":2710,"contentlist":[{"channelId":"5572a109b3cdc86cf39001db","channelName":"国内最新","content":"","desc":"","html":"","imageurls":[{"height":250,"url":"http://image20.it168.com/201604_800x800/2502/f4afc28a07a8a151.jpg","width":546}],"link":"http://elec.it168.com/a2016/0428/2619/000002619879.shtml","nid":"10826203206257278541","pubDate":"2016-04-28 15:29:18","sentiment_display":0,"sentiment_tag":{"count":"14547","dim":"0","id":"934","isbooked":0,"ishot":"0","name":"Haier","type":"senti"},"source":"IT168","title":"KKR做红娘，促成海尔与易果战略合作"}],"currentPage":1,"maxResult":20}
     * ret_code : 0
     */

    private PagebeanEntity pagebean;
    private int ret_code;

    public void setPagebean(PagebeanEntity pagebean) {
        this.pagebean = pagebean;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public PagebeanEntity getPagebean() {
        return pagebean;
    }

    public int getRet_code() {
        return ret_code;
    }

    public static class PagebeanEntity implements Serializable{
        /**
         * allNum : 54188
         * allPages : 2710
         * contentlist : [{"channelId":"5572a109b3cdc86cf39001db","channelName":"国内最新","content":"","desc":"","html":"","imageurls":[{"height":250,"url":"http://image20.it168.com/201604_800x800/2502/f4afc28a07a8a151.jpg","width":546}],"link":"http://elec.it168.com/a2016/0428/2619/000002619879.shtml","nid":"10826203206257278541","pubDate":"2016-04-28 15:29:18","sentiment_display":0,"sentiment_tag":{"count":"14547","dim":"0","id":"934","isbooked":0,"ishot":"0","name":"Haier","type":"senti"},"source":"IT168","title":"KKR做红娘，促成海尔与易果战略合作"}]
         * currentPage : 1
         * maxResult : 20
         */

        private int allNum;
        private int allPages;
        private int currentPage;
        private int maxResult;
        private List<ContentlistEntity> contentlist;

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public void setContentlist(List<ContentlistEntity> contentlist) {
            this.contentlist = contentlist;
        }

        public int getAllNum() {
            return allNum;
        }

        public int getAllPages() {
            return allPages;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getMaxResult() {
            return maxResult;
        }

        public List<ContentlistEntity> getContentlist() {
            return contentlist;
        }

        public static class ContentlistEntity implements Serializable{
            /**
             * channelId : 5572a109b3cdc86cf39001db
             * channelName : 国内最新
             * content :
             * desc :
             * html :
             * imageurls : [{"height":250,"url":"http://image20.it168.com/201604_800x800/2502/f4afc28a07a8a151.jpg","width":546}]
             * link : http://elec.it168.com/a2016/0428/2619/000002619879.shtml
             * nid : 10826203206257278541
             * pubDate : 2016-04-28 15:29:18
             * sentiment_display : 0
             * sentiment_tag : {"count":"14547","dim":"0","id":"934","isbooked":0,"ishot":"0","name":"Haier","type":"senti"}
             * source : IT168
             * title : KKR做红娘，促成海尔与易果战略合作
             */

            private String channelId;
            private String channelName;
            private String content;
            private String desc;
            private String html;
            private String link;
            private String nid;
            private String pubDate;
            private int sentiment_display;
            private SentimentTagEntity sentiment_tag;
            private String source;
            private String title;
            private List<ImageurlsEntity> imageurls;

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public void setHtml(String html) {
                this.html = html;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public void setNid(String nid) {
                this.nid = nid;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public void setSentiment_display(int sentiment_display) {
                this.sentiment_display = sentiment_display;
            }

            public void setSentiment_tag(SentimentTagEntity sentiment_tag) {
                this.sentiment_tag = sentiment_tag;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setImageurls(List<ImageurlsEntity> imageurls) {
                this.imageurls = imageurls;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getChannelName() {
                return channelName;
            }

            public String getContent() {
                return content;
            }

            public String getDesc() {
                return desc;
            }

            public String getHtml() {
                return html;
            }

            public String getLink() {
                return link;
            }

            public String getNid() {
                return nid;
            }

            public String getPubDate() {
                return pubDate;
            }

            public int getSentiment_display() {
                return sentiment_display;
            }

            public SentimentTagEntity getSentiment_tag() {
                return sentiment_tag;
            }

            public String getSource() {
                return source;
            }

            public String getTitle() {
                return title;
            }

            public List<ImageurlsEntity> getImageurls() {
                return imageurls;
            }

            public static class SentimentTagEntity implements Serializable{
                /**
                 * count : 14547
                 * dim : 0
                 * id : 934
                 * isbooked : 0
                 * ishot : 0
                 * name : Haier
                 * type : senti
                 */

                private String count;
                private String dim;
                private String id;
                private int isbooked;
                private String ishot;
                private String name;
                private String type;

                public void setCount(String count) {
                    this.count = count;
                }

                public void setDim(String dim) {
                    this.dim = dim;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setIsbooked(int isbooked) {
                    this.isbooked = isbooked;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getCount() {
                    return count;
                }

                public String getDim() {
                    return dim;
                }

                public String getId() {
                    return id;
                }

                public int getIsbooked() {
                    return isbooked;
                }

                public String getIshot() {
                    return ishot;
                }

                public String getName() {
                    return name;
                }

                public String getType() {
                    return type;
                }
            }

            public static class ImageurlsEntity implements Serializable{
                /**
                 * height : 250
                 * url : http://image20.it168.com/201604_800x800/2502/f4afc28a07a8a151.jpg
                 * width : 546
                 */

                private int height;
                private String url;
                private int width;

                public void setHeight(int height) {
                    this.height = height;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public String getUrl() {
                    return url;
                }

                public int getWidth() {
                    return width;
                }
            }
        }
    }
}
