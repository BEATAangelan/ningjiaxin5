package ningjiaxin1.bwie.com.njx20181208.bean;

public class User {
        private String msg;
        private int code;
        private DataBean data;

        public String getMsg() {

            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            private String mobile;
            private String Password;

            public DataBean(String mobile, String password) {
                this.mobile = mobile;
                Password = password;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPassword() {
                return Password;
            }

            public void setPassword(String password) {
                Password = password;
            }
        }
}
