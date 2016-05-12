package com.example.aoiumi.memoapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * メインアクティビティ
 * メモ帳アプリのメインプログラム
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // ----------fields----------

    /** タイトル入力用エディットテキスト */
    private EditText titleEditText;
    /** コメント入力用エディットテキスト */
    private EditText commentEditText;
    /**日付用入力テキスト*/
    private EditText hizukeEditText;

    /** プリファレンス */
    private SharedPreferences preferences;

    /** タイトル保存用キー */
    private static final String KEY_TITLE = "title";
    /** コメント保存用キー */
    private static final String KEY_COMMENT = "comment";
    /**日付保存用キー*/
    private static final String KEY_HIZUKE = "hizuke";
    /** 何も保存されていない時に返す文字列 */
    private static final String NOT_FOUND_DATA = "データが見つかりません。";

    // ----------methods----------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // レイアウトよりEditTextを取得
        titleEditText = (EditText) findViewById(R.id.main_title_etx);
        commentEditText = (EditText) findViewById(R.id.main_comment_etx);
        hizukeEditText = (EditText) findViewById(R.id.main_hizuke);

        // プリファレンスをデフォルト名で作成
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // プリファレンスにデータが保存されていれば、保存されているデータをセットする
        // タイトル
        String title = preferences.getString(KEY_TITLE, NOT_FOUND_DATA);
        if (!title.equals(NOT_FOUND_DATA)) {
            titleEditText.setText(title);
        }
        // コメント
        String comment = preferences.getString(KEY_COMMENT, NOT_FOUND_DATA);
        if (!comment.equals(NOT_FOUND_DATA)) {
            commentEditText.setText(comment);
        }

        //日付
        String haco = preferences.getString(KEY_COMMENT, "");
        hizukeEditText.setText(haco);

        //レイアウトより、保存ボタンを取得
        Button saveBtn = (Button) findViewById(R.id.main_save_btn);
        saveBtn.setOnClickListener(this);

        //レイアウトより、削除ボタンを取得
        Button deleteBtn =(Button)findViewById(R.id.main_delete_btn);
        deleteBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        // プリファレンスエディタの初期化
        SharedPreferences.Editor editor = preferences.edit();
        switch (view.getId()) {
            case R.id.main_save_btn:
                // 入力されているデータを取得
                String title = titleEditText.getText().toString();
                String comment = commentEditText.getText().toString();
                String hizuke = hizukeEditText.getText().toString();
                // プリファレンスにデータを保存
                editor.putString(KEY_TITLE, title);
                editor.putString(KEY_COMMENT, comment);
                editor.putString(KEY_HIZUKE,hizuke);
                editor.commit();


                // Toastを表示し、保存が完了した旨を通知する
                Toast.makeText(this, "保存しました。", Toast.LENGTH_SHORT).show();

                //削除ボタンが押された場合
                case R.id.main_delete_btn:

                    //プリファレンスからデータを削除
                    editor.remove(KEY_COMMENT);
                    editor.remove(KEY_TITLE);
                    editor.remove(KEY_HIZUKE);
                    editor.commit();//上書き保存

                    //入力欄を空にする
                    titleEditText.setText("");
                    commentEditText.setText("");
                    hizukeEditText.setText("");
                    //Toastを表示し、削除された旨を表示
                    Toast.makeText(this,"削除しました。",Toast.LENGTH_SHORT).show();
                    break;






        }
    }
}