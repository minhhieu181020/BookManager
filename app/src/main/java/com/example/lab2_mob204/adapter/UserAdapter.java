package com.example.lab2_mob204.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.dao.UserDAO;
import com.example.lab2_mob204.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHloder> {
    private Context context;
    private List<User> userList;
    private RecyclerView rvList;
    private UserDAO userDAO;
    private UserAdapter userAdapter;
    private EditText tvUserNameDetail, tvPhoneDetail, tvFullNameDetail;
    private Button btnCancel, btnEditUser;


    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;

    }

    @NonNull
    @Override
    public UserHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rvuser, parent, false);
        UserHloder userHloder = new UserHloder(view);

        return userHloder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserHloder holder, final int position) {
        userDAO = new UserDAO(context);
        holder.tvText1.setText("Tên nguời dùng: " + userList.get(position).getUsername());

        holder.tvText2.setText("Số điện thoại: " + userList.get(position).getPhone());

        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean result = userDAO.isDelete(userList.get(position).getUsername());
                if (result) {
                    userList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setView(R.layout.activity_user_detail);

                final AlertDialog alertDialog1 = alertDialog.show();

                tvUserNameDetail = alertDialog1.findViewById(R.id.tvUserNameDetail);

                tvUserNameDetail.setText(userList.get(position).getUsername());

                tvPhoneDetail = alertDialog1.findViewById(R.id.tvPhoneDetail);

                tvPhoneDetail.setText(userList.get(position).getPhone());

                tvFullNameDetail = alertDialog1.findViewById(R.id.tvFullNameDetail);

                tvFullNameDetail.setText(userList.get(position).getFullname());

                btnCancel = alertDialog1.findViewById(R.id.btnCancel);

                btnEditUser = alertDialog1.findViewById(R.id.btnEditUser);

                btnEditUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newUsername = tvUserNameDetail.getText().toString().trim();
                        String newFullname = tvFullNameDetail.getText().toString().trim();
                        String newPhone = tvPhoneDetail.getText().toString().trim();

                        if (newUsername.equals("")) {
                            tvUserNameDetail.setError("Bạn cần nhập tên");
                            tvUserNameDetail.requestFocus();
                            return;

                        } else if (newFullname.equals("")) {
                            tvFullNameDetail.setError("Bạn cần nhập đầy đủ họ tên");
                            tvFullNameDetail.requestFocus();
                            return;
                        } else if (newPhone.equals("")) {
                            tvPhoneDetail.setError("Bạn cần nhập số điện thoại");
                            tvPhoneDetail.requestFocus();
                            return;

                        } else {
                            User user = new User();
                            user.setUsername(newUsername);
                            user.setFullname(newFullname);
                            user.setPhone(newPhone);
                            boolean result = userDAO.updateUser(user);
                            if (result) {
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                userList = userDAO.selectUser();
                                userAdapter =new UserAdapter(context,userList);
                                notifyDataSetChanged();
                                alertDialog1.dismiss();
                            } else {
                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvUserNameDetail.setText("");
                        tvPhoneDetail.setText("");
                        tvFullNameDetail.setText("");
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
