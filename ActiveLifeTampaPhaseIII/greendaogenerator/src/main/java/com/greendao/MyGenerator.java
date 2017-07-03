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
        // addPhonesEntities(schema);
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
        return  scheduleData;
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
        return  hoursData;
    }
    // This is use to describe the colums of your table
    private static Entity addScheduleDateDataEntities(final Schema schema) {
        Entity scheduleDateData = schema.addEntity("ScheduleDateData");
        scheduleDateData.addIdProperty().primaryKey().autoincrement();
        scheduleDateData.addIntProperty("schedule_id").notNull();
        scheduleDateData.addStringProperty("schedule_name");
        scheduleDateData.addStringProperty("class_id");
        scheduleDateData.addStringProperty("class_name");
        scheduleDateData.addStringProperty("schedule_type_id");
        scheduleDateData.addStringProperty("schedule_type");
        scheduleDateData.addStringProperty("schedule_start_time");
        scheduleDateData.addStringProperty("schedule_end_time");
        scheduleDateData.addBooleanProperty("schedule_monday");
        scheduleDateData.addBooleanProperty("schedule_tuesday");
        scheduleDateData.addBooleanProperty("schedule_wednesday");
        scheduleDateData.addBooleanProperty("schedule_thursday");
        scheduleDateData.addBooleanProperty("schedule_friday");
        scheduleDateData.addBooleanProperty("schedule_saturday");
        scheduleDateData.addBooleanProperty("schedule_sunday");
        scheduleDateData.addStringProperty("schedule_frequency");
        scheduleDateData.addBooleanProperty("is_cancelled");
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
