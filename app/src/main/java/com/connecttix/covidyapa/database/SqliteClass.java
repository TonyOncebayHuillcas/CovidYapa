package com.connecttix.covidyapa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.connecttix.covidyapa.models.ProductModel;
import com.connecttix.covidyapa.models.TiendaModel;

import java.io.File;
import java.util.ArrayList;

public class SqliteClass {
    public DatabaseHelper databasehelp;
    private static SqliteClass SqliteInstance = null;

    private SqliteClass(Context context) {
        databasehelp = new DatabaseHelper(context);
    }

    public static SqliteClass getInstance(Context context) {
        if (SqliteInstance == null) {
            SqliteInstance = new SqliteClass(context);
        }
        return SqliteInstance;
    }
    public class DatabaseHelper extends SQLiteOpenHelper {
        public Context context;
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "app_covid.db";

        /* @TABLE_APP_PRODUCT */
        public static final String TABLE_APP_PRODUCT = "app_products";
        public static final String KEY_PROID = "ap_id";
        public static final String KEY_PROIDSTO = "ap_id_store";
        public static final String KEY_PRONAM = "ap_name";
        public static final String KEY_PROPRE = "ap_precio";
        public static final String KEY_PRODES = "ap_description";
        public static final String KEY_PROSTO = "ap_stock";
        public static final String KEY_PROCAT = "ap_categoria";

        /* @TABLE_APP_TIENDA */
        public static final String TABLE_APP_STORE = "app_stores";
        public static final String KEY_STOID = "as_id";
        public static final String KEY_STONAM = "as_name";
        public static final String KEY_STOLAT = "as_latitud";
        public static final String KEY_STOLON = "as_longitud";
        public static final String KEY_STODIR = "as_direccion";
        public static final String KEY_STORUC = "as_ruc";
        public static final String KEY_STOCOR = "as_correo";
        public static final String KEY_STOCAT = "as_categoria";

        public AppProductSql productSql;
        public AppStoreSql storeSql;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            productSql = new AppProductSql();
            storeSql = new AppStoreSql();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            /* @TABLE_PRODUCT */
            String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_APP_PRODUCT + "("
                    + KEY_PROID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PROIDSTO + " TEXT," + KEY_PRONAM + " TEXT,"
                    + KEY_PROPRE + " TEXT,"
                    + KEY_PRODES + " TEXT,"  + KEY_PROSTO + " TEXT," +KEY_PROCAT + " TEXT )";

            /* @TABLE_STORE */
            String CREATE_TABLE_STORE = "CREATE TABLE " + TABLE_APP_STORE + "("
                    + KEY_STOID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STONAM + " TEXT,"
                    + KEY_STOLAT + " TEXT," + KEY_STOLON + " TEXT," + KEY_STODIR + " TEXT,"
                    + KEY_STOCOR + " TEXT,"  + KEY_STORUC + " TEXT," +KEY_STOCAT + " TEXT )";

            db.execSQL(CREATE_TABLE_PRODUCT);
            db.execSQL(CREATE_TABLE_STORE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_APP_PRODUCT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_APP_STORE);
        }

        public boolean checkDataBase(){
            File dbFile = new File(context.getDatabasePath(DATABASE_NAME).toString());
            return dbFile.exists();
        }
        public void deleteDataBase(){
            context.deleteDatabase(DATABASE_NAME);
        }

        public class AppProductSql{
            public AppProductSql() {}

            public void deleteProducts() {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                db.delete(TABLE_APP_PRODUCT, null, null);
                db.close();
            }

            public void addProduct(ProductModel product){
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();
                //KEY_PROPRE
                values.put(KEY_PROIDSTO, product.getId_tienda());
                values.put(KEY_PRONAM, product.getNombre());
                values.put(KEY_PROPRE, product.getPrecio());
                values.put(KEY_PRODES, product.getDescripcion());
                values.put(KEY_PROSTO, product.getStock());
                values.put(KEY_PROCAT, product.getCategoria_producto());
                db.insert(TABLE_APP_PRODUCT, null, values);
                db.close();

            }

            public ProductModel getProduct(String id){
                ProductModel model = new ProductModel();
                String selectQuery = "SELECT * FROM " + TABLE_APP_PRODUCT + " WHERE " + KEY_PROID + "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    model.setId(cursor.getInt(cursor.getColumnIndex(KEY_PROID)));
                    model.setId_tienda(cursor.getInt(cursor.getColumnIndex(KEY_PROIDSTO)));
                    model.setNombre(cursor.getString(cursor.getColumnIndex(KEY_PRONAM)));
                    model.setPrecio(cursor.getString(cursor.getColumnIndex(KEY_PROPRE)));
                    model.setDescripcion(cursor.getString(cursor.getColumnIndex(KEY_PRODES)));
                    model.setStock(cursor.getInt(cursor.getColumnIndex(KEY_PROSTO)));
                    model.setCategoria_producto(cursor.getString(cursor.getColumnIndex(KEY_PROCAT)));
                }

                cursor.close();
                db.close();
                return model;

            }

            public ArrayList<ProductModel> getAllSites() {
                ArrayList<ProductModel> models = new ArrayList<ProductModel>();
                String selectQuery = "SELECT * FROM " + TABLE_APP_PRODUCT;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {

                    do{
                        ProductModel model = new ProductModel();
                        model.setId(cursor.getInt(cursor.getColumnIndex(KEY_PROID)));
                        model.setId_tienda(cursor.getInt(cursor.getColumnIndex(KEY_PROIDSTO)));
                        model.setNombre(cursor.getString(cursor.getColumnIndex(KEY_PRONAM)));
                        model.setPrecio(cursor.getString(cursor.getColumnIndex(KEY_PROPRE)));
                        model.setDescripcion(cursor.getString(cursor.getColumnIndex(KEY_PRODES)));
                        model.setStock(cursor.getInt(cursor.getColumnIndex(KEY_PROSTO)));
                        model.setCategoria_producto(cursor.getString(cursor.getColumnIndex(KEY_PROCAT)));

                        models.add(model);
                    } while (cursor.moveToNext());

                }
                cursor.close();
                db.close();
                return models;
            }

        }

        public class AppStoreSql{
            public void deleteStore() {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                db.delete(TABLE_APP_STORE, null, null);
                db.close();
            }

            public void addStore(TiendaModel store){
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(KEY_STONAM, store.getNombre());
                values.put(KEY_STOLAT, store.getLatitud());
                values.put(KEY_STOLON, store.getLongitud());
                values.put(KEY_STODIR, store.getDirección());
                values.put(KEY_STOCOR, store.getCorreo());
                values.put(KEY_STORUC, store.getRuc());
                values.put(KEY_STOCAT, store.getCategoria_tienda());
                db.insert(TABLE_APP_STORE, null, values);
                db.close();

            }

            public TiendaModel getStore(String id){
                TiendaModel model = new TiendaModel();
                String selectQuery = "SELECT * FROM " + TABLE_APP_STORE + " WHERE " + KEY_STOID + "='" + id + "'" ;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    model.setId(cursor.getInt(cursor.getColumnIndex(KEY_STOID)));
                    model.setNombre(cursor.getString(cursor.getColumnIndex(KEY_STONAM)));
                    model.setLatitud(cursor.getString(cursor.getColumnIndex(KEY_STOLAT)));
                    model.setLongitud(cursor.getString(cursor.getColumnIndex(KEY_STOLON)));
                    model.setDirección(cursor.getString(cursor.getColumnIndex(KEY_STODIR)));
                    model.setCorreo(cursor.getString(cursor.getColumnIndex(KEY_STOCOR)));
                    model.setRuc(cursor.getString(cursor.getColumnIndex(KEY_STORUC)));
                    model.setCategoria_tienda(cursor.getString(cursor.getColumnIndex(KEY_STOCAT)));

                }

                cursor.close();
                db.close();
                return model;

            }

            public ArrayList<TiendaModel> getAllStore() {
                ArrayList<TiendaModel> models = new ArrayList<TiendaModel>();
                String selectQuery = "SELECT * FROM " + TABLE_APP_STORE;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {

                    do{
                        TiendaModel model = new TiendaModel();
                        model.setId(cursor.getInt(cursor.getColumnIndex(KEY_STOID)));
                        model.setNombre(cursor.getString(cursor.getColumnIndex(KEY_STONAM)));
                        model.setLatitud(cursor.getString(cursor.getColumnIndex(KEY_STOLAT)));
                        model.setLongitud(cursor.getString(cursor.getColumnIndex(KEY_STOLON)));
                        model.setDirección(cursor.getString(cursor.getColumnIndex(KEY_STODIR)));
                        model.setCorreo(cursor.getString(cursor.getColumnIndex(KEY_STOCOR)));
                        model.setRuc(cursor.getString(cursor.getColumnIndex(KEY_STORUC)));
                        model.setCategoria_tienda(cursor.getString(cursor.getColumnIndex(KEY_STOCAT)));

                        models.add(model);
                    } while (cursor.moveToNext());

                }
                cursor.close();
                db.close();
                return models;
            }
        }

    }
}
