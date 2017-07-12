package com.greendao;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.android.activelife.tampa.db"); // Your app package name and the (.db) is the folder where the DAO files will be generated into.
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addScheduleDateDataEntities(schema);
        addClassesEntities(schema);
        addInstructorEntities(schema);
        addScheduleDataEntities(schema);
        addHoursDataEntities(schema);
        addLocationEntities(schema);
        addDefaultLocationEntities(schema);
        addLocationsEntities(schema);
        addMessagesEntities(schema);
        addMemberEntities(schema);
        addMessageLocationEntities(schema);
    }

    private static Entity addMemberEntities(final Schema schema) {
        Entity messageData = schema.addEntity("MemberData");
        messageData.addStringProperty("member_id").primaryKey();
        messageData.addStringProperty("member_name");
        return messageData;
    }

    private static Entity addMessagesEntities(final Schema schema) {
        Entity messageData = schema.addEntity("MessagesData");
        messageData.addIdProperty().primaryKey().autoincrement();
        messageData.addStringProperty("message_id");
        messageData.addStringProperty("message_title");
        messageData.addStringProperty("message_msg");
        messageData.addStringProperty("message_send_at");


        return messageData;
    }

    private static Entity addMessageLocationEntities(final Schema schema) {
        Entity messageData = schema.addEntity("MessageLocationData");
        messageData.addIdProperty().primaryKey().autoincrement();
        messageData.addStringProperty("message_id");
        messageData.addStringProperty("message_title");
        messageData.addStringProperty("message_msg");
        messageData.addStringProperty("message_send_at");
        messageData.addStringProperty("location_id");
        messageData.addStringProperty("location_name");
        messageData.addStringProperty("location_address");
        messageData.addStringProperty("location_city");
        messageData.addStringProperty("location_state");
        messageData.addStringProperty("location_zip");
        messageData.addStringProperty("location_phone");
        messageData.addStringProperty("location_email");
        return messageData;
    }

    private static Entity addLocationsEntities(final Schema schema) {
        Entity locationData = schema.addEntity("LocationsData");
        locationData.addIdProperty().primaryKey().autoincrement();
        locationData.addStringProperty("location_id");
        locationData.addStringProperty("location_name");
        locationData.addStringProperty("location_address");
        locationData.addStringProperty("location_city");
        locationData.addStringProperty("location_state");
        locationData.addStringProperty("location_zip");
        locationData.addStringProperty("location_phone");
        locationData.addStringProperty("location_email");
        locationData.addStringProperty("location_program_link");
        locationData.addStringProperty("location_donate_link");

        return locationData;
    }

    private static Entity addLocationEntities(final Schema schema) {
        Entity locationData = schema.addEntity("LocationData");
        locationData.addIdProperty().primaryKey().autoincrement();
        locationData.addIntProperty("postion");
        locationData.addStringProperty("location_id");
        locationData.addStringProperty("location_name");
        locationData.addStringProperty("location_address");
        locationData.addStringProperty("location_city");
        locationData.addStringProperty("location_state");
        locationData.addStringProperty("location_zip");
        locationData.addStringProperty("location_phone");
        locationData.addStringProperty("location_email");
        locationData.addStringProperty("location_program_link");
        locationData.addStringProperty("location_donate_link");

        return locationData;
    }

    private static Entity addDefaultLocationEntities(final Schema schema) {
        Entity locationData = schema.addEntity("DefaultLocationData");
        locationData.addIdProperty().primaryKey().autoincrement();
        locationData.addIntProperty("postion");
        locationData.addStringProperty("location_id");
        locationData.addStringProperty("location_name");
        locationData.addStringProperty("location_address");
        locationData.addStringProperty("location_city");
        locationData.addStringProperty("location_state");
        locationData.addStringProperty("location_zip");
        locationData.addStringProperty("location_phone");
        locationData.addStringProperty("location_email");
        locationData.addStringProperty("location_program_link");
        locationData.addStringProperty("location_donate_link");

        return locationData;
    }

    private static Entity addClassesEntities(final Schema schema) {
        Entity classData = schema.addEntity("ClassData");
        classData.addIdProperty().primaryKey().autoincrement();
        classData.addStringProperty("class_id");
        classData.addStringProperty("class_name");
        classData.addStringProperty("class_description");
        return classData;
    }

    private static Entity addInstructorEntities(final Schema schema) {
        Entity instructorData = schema.addEntity("InstructorData");
        instructorData.addIdProperty().primaryKey().autoincrement();
        instructorData.addStringProperty("instructor_id");
        instructorData.addStringProperty("instructor_name");
        return instructorData;
    }

    private static Entity addScheduleDataEntities(final Schema schema) {
        Entity scheduleData = schema.addEntity("ScheduleData");
        scheduleData.addIdProperty().primaryKey().autoincrement();
        scheduleData.addStringProperty("schedule_type_id");
        scheduleData.addStringProperty("schedule_type");
        return scheduleData;
    }

    private static Entity addHoursDataEntities(final Schema schema) {
        Entity hoursData = schema.addEntity("HoursData");
        hoursData.addIdProperty().primaryKey().autoincrement();
        hoursData.addStringProperty("hours_name");
        hoursData.addStringProperty("hour_monday_start_time");
        hoursData.addStringProperty("hour_monday_end_time");
        hoursData.addStringProperty("hour_tuesday_start_time");
        hoursData.addStringProperty("hour_tuesday_end_time");
        hoursData.addStringProperty("hour_wednesday_start_time");
        hoursData.addStringProperty("hour_wednesday_end_time");
        hoursData.addStringProperty("hour_thursday_start_time");
        hoursData.addStringProperty("hour_thursday_end_time");
        hoursData.addStringProperty("hour_friday_start_time");
        hoursData.addStringProperty("hour_friday_end_time");
        hoursData.addStringProperty("hour_saturday_start_time");
        hoursData.addStringProperty("hour_saturday_end_time");
        hoursData.addStringProperty("hour_sunday_start_time");
        hoursData.addStringProperty("hour_sunday_end_time");
        return hoursData;
    }

    // This is use to describe the colums of your table
    private static Entity addScheduleDateDataEntities(final Schema schema) {
        Entity scheduleDateData = schema.addEntity("ScheduleDateData");
        scheduleDateData.addIdProperty().primaryKey().autoincrement();
        scheduleDateData.addIntProperty("schedule_id").notNull();
        scheduleDateData.addStringProperty("schedule_name");
        scheduleDateData.addStringProperty("class_id");
        scheduleDateData.addStringProperty("class_name");
        scheduleDateData.addStringProperty("class_desc");
        scheduleDateData.addStringProperty("schedule_type_id");
        scheduleDateData.addStringProperty("schedule_type");
        scheduleDateData.addStringProperty("schedule_start_date");
        scheduleDateData.addStringProperty("schedule_start_time");
        scheduleDateData.addStringProperty("schedule_end_time");
        scheduleDateData.addLongProperty("schedule_start_time_long");
        scheduleDateData.addLongProperty("schedule_end_time_long");
        scheduleDateData.addIntProperty("schedule_monday");
        scheduleDateData.addIntProperty("schedule_tuesday");
        scheduleDateData.addIntProperty("schedule_wednesday");
        scheduleDateData.addIntProperty("schedule_thursday");
        scheduleDateData.addIntProperty("schedule_friday");
        scheduleDateData.addIntProperty("schedule_saturday");
        scheduleDateData.addIntProperty("schedule_sunday");
        scheduleDateData.addStringProperty("schedule_frequency");
        scheduleDateData.addIntProperty("is_cancelled");
        scheduleDateData.addIntProperty("is_reservable");
        scheduleDateData.addStringProperty("instructor_id");
        scheduleDateData.addStringProperty("instructor_name");
        scheduleDateData.addStringProperty("location_id");
        scheduleDateData.addStringProperty("location_name");

        return scheduleDateData;
    }

    //    private static Entity addPhonesEntities(final Schema schema) {
    //        Entity phone = schema.addEntity("Phone");
    //        phone.addIdProperty().primaryKey().autoincrement();
    //        phone.addIntProperty("user_id").notNull();
    //        phone.addStringProperty("number");
    //        return phone;
    //    }
}
