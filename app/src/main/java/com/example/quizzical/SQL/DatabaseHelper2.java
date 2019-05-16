package com.example.quizzical.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizzical.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "QuestionsManager2.db";

    // Question table name
    private static final String TABLE_QUESTION = "question";

    // Question Table Columns names
    private static final String COLUMN_QUESTION_ID = "question_id";
    private static final String COLUMN_QUESTION_QUESTION = "question_question";
    private static final String COLUMN_QUESTION_ANSWER1= "question_answer1";
    private static final String COLUMN_QUESTION_ANSWER2= "question_answer2";
    private static final String COLUMN_QUESTION_ANSWER3= "question_answer3";
    private static final String COLUMN_QUESTION_ANSWER4= "question_answer4";
    private static final String COLUMN_QUESTION_CORRECT= "question_correct";

    // create table sql query
    private String CREATE_QUESTION_TABLE = "CREATE TABLE " + TABLE_QUESTION + "("
            + COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_QUESTION_QUESTION + " TEXT,"
            + COLUMN_QUESTION_ANSWER1 + " TEXT," + COLUMN_QUESTION_ANSWER2 + " TEXT," + COLUMN_QUESTION_ANSWER3 + " TEXT," + COLUMN_QUESTION_ANSWER4 + " TEXT," + COLUMN_QUESTION_CORRECT + " INTEGER" + ")";

    // drop table sql query
    private String DROP_QUESTION_TABLE = "DROP TABLE IF EXISTS " + TABLE_QUESTION;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUESTION_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop question Table if exist
        db.execSQL(DROP_QUESTION_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create question record
     *
     * @param question
     */
    public void addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_QUESTION, question.getQuestion());
        values.put(COLUMN_QUESTION_ANSWER1, question.getAnswer1());
        values.put(COLUMN_QUESTION_ANSWER2, question.getAnswer2());
        values.put(COLUMN_QUESTION_ANSWER3, question.getAnswer3());
        values.put(COLUMN_QUESTION_ANSWER4, question.getAnswer4());
        values.put(COLUMN_QUESTION_CORRECT, question.getCorrect());

        // Inserting Row
        db.insert(TABLE_QUESTION, null, values);
        db.close();
    }

    /**
     * This method is to fetch all question and return the list of question records
     *
     * @return list
     */
    public List<Question> getAllQuestion() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_QUESTION_ID,
                COLUMN_QUESTION_QUESTION,
                COLUMN_QUESTION_ANSWER1,
                COLUMN_QUESTION_ANSWER2,
                COLUMN_QUESTION_ANSWER3,
                COLUMN_QUESTION_ANSWER4,
                COLUMN_QUESTION_CORRECT
        };
        // sorting orders
        String sortOrder =
                COLUMN_QUESTION_ID + " ASC";
        List<Question> questionList = new ArrayList<Question>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the question table
        /**
         * Here query function is used to fetch records from question table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT question_id,question_question,question_answer1,question_answer2,question_answer3,question_answer4 FROM user ORDER BY question_id;
         */
        Cursor cursor = db.query(TABLE_QUESTION, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_ID))));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_QUESTION)));
                question.setAnswer1(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_ANSWER1)));
                question.setAnswer2(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_ANSWER2)));
                question.setAnswer3(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_ANSWER3)));
                question.setAnswer4(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_ANSWER4)));
                question.setCorrect(cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_CORRECT)));
                // Adding question record to list
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return questionlist
        return questionList;
    }

    /**
     * This method to update question record
     *
     * @param question
     */
    public void updateQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION_QUESTION, question.getQuestion());
        values.put(COLUMN_QUESTION_ANSWER1, question.getAnswer1());
        values.put(COLUMN_QUESTION_ANSWER2, question.getAnswer2());
        values.put(COLUMN_QUESTION_ANSWER3, question.getAnswer3());
        values.put(COLUMN_QUESTION_ANSWER4, question.getAnswer4());
        values.put(COLUMN_QUESTION_CORRECT, question.getCorrect());

        // updating row
        db.update(TABLE_QUESTION, values, COLUMN_QUESTION_ID + " = ?",
                new String[]{String.valueOf(question.getId())});
        db.close();
    }

    /**
     * This method is to delete question record
     *
     * @param question
     */
    public void deleteQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete question record by id
        db.delete(TABLE_QUESTION, COLUMN_QUESTION_ID + " = ?",
                new String[]{String.valueOf(question.getId())});
        db.close();
    }

    /**
     * This method to check question exist or not
     *
     * @param question
     * @return true/false
     */
    public boolean checkQuestion(String question) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_QUESTION_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_QUESTION_QUESTION + " = ?";

        // selection argument
        String[] selectionArgs = {question};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_QUESTION, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

}
