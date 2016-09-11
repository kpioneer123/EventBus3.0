package com.losileeya.eventbusapp.event;

/**
 * Created by Administrator on 2015/12/14.
 */
public class EventBusEvents {

    public static class ListViewEvent{
        private String value;

        public ListViewEvent(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    public static class FirstEvent{
        private String value;

        public FirstEvent(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public static class ThirdEvent{
        private String value;

        public ThirdEvent(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


}
