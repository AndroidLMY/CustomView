package com.example.lmy.customview.MMKV;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lmy.customview.R;
import com.example.lmy.customview.Utils.LogUtil;
import com.example.lmy.customview.base.BaseActivity;
import com.tencent.mmkv.MMKV;

import java.io.Serializable;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MMKVActivity extends BaseActivity {

    @BindView(R.id.button_01)
    Button button01;
    @BindView(R.id.button_02)
    Button button02;
    @BindView(R.id.button_03)
    Button button03;
    @BindView(R.id.button_04)
    Button button04;
    public MMKV kv;
    @BindView(R.id.tv_content)
    TextView tvContent;

    public static void show(Context context) {
        context.startActivity(new Intent(context, MMKVActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_m_k_v);
        ButterKnife.bind(this);
        kv = MmkvUtils.getInstance().getMmkv("Ceshi");


    }

    @OnClick({R.id.button_01, R.id.button_02, R.id.button_03, R.id.button_04})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_01:
                kv.encode("kvboole", true);
                kv.encode("kvint", 10);
                kv.encode("kvfloat", 1.0f);
                kv.encode("kvdouble", 10.21);
                kv.encode("kvlong", 2L);
                kv.encode("kvstring", "李明洋");
                byte[] bytes = {'m', 'm', 'k', 'v'};
                kv.encode("kvbytes", bytes);
                Set<String> setSet = new ArraySet<>();
                setSet.add("1");
                setSet.add("2");
                setSet.add("3");
                kv.encode("kvset", setSet);
                kv.encode("kvobject", new Student("李明", "男", 18));
                kv.encode("kvstring", "修改值Ceshi");
                break;
            case R.id.button_02:
                break;
            case R.id.button_03:
                boolean bValue = kv.decodeBool("bool");
//                tvContent.setText(kv.decodeParcelable("student", Student.class).toString());

//                tvContent.setText(kv.decodeString("kvstring"));

                LogUtil.e(MmkvUtils.
                        getInstance().
                        getdefaultMmkv().
                        decodeString("kvstring"));
                LogUtil.e(MmkvUtils.
                        getInstance().
                        getMmkv("Ceshi").
                        decodeString("kvstring"));
                break;
            case R.id.button_04:
                break;
            default:
                break;
        }
    }


    /**
     * 如果需要保存对象需要实现Parcelable接口
     */
    static class Student implements Parcelable {
        String name;
        String sex;
        int age;

        public Student(String name, String sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        protected Student(Parcel in) {
            name = in.readString();
            sex = in.readString();
            age = in.readInt();
        }

        public static final Creator<Student> CREATOR = new Creator<Student>() {
            @Override
            public Student createFromParcel(Parcel in) {
                return new Student(in);
            }

            @Override
            public Student[] newArray(int size) {
                return new Student[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(sex);
            dest.writeInt(age);
        }
    }
}
