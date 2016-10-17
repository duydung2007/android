package com.example.bai17_autocompletetext;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends Activity implements TextWatcher {
    TextView selection;
    // Khai báo 2 CompleteTextView
    AutoCompleteTextView singleComplete;
    MultiAutoCompleteTextView multiComplete;
    // Khởi tạo mảng tạm để Test
    String arr[] = { "hà nội", "Huế", "Sài gòn", "hà giang", "Hội an",
            "Kiên giang", "Lâm đồng", "Long khánh" };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.selection);
        // lấy đối tượng AutoCompleteTextView ra
        singleComplete = (AutoCompleteTextView) findViewById(R.id.editAuto);
        // Thiết lập để lắng nghe TextChange
        singleComplete.addTextChangedListener(this);
        // Thiết lập ArrayADapter
        singleComplete.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr));
        // Lấy đối tượng MultiAutoCompleteTextView ra
        multiComplete = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
        // Thiết lập ArrayADapter
        multiComplete.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr));
        // Đối với MultiAutoCompleteTextView bắt buộc phải gọi dòng lệnh này
        multiComplete
                .setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    // Khi chọn trong AutoCompleteTextView hàm này sẽ tự động phát sinh
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        selection.setText(singleComplete.getText());
    }

    public void afterTextChanged(Editable arg0) {
    }

    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
            int arg3) {
    }
}
