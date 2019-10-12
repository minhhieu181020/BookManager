package com.example.lab2_mob204.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.dao.TypeBookDAO;
import com.example.lab2_mob204.model.TypeBook;

import java.util.List;

public class TypeBookApdapter extends RecyclerView.Adapter<TypeBookHolder> {
    private Context context;
    private RecyclerView recyclerView;
    private TypeBookApdapter typeBookApdapter;
    private TypeBookDAO typeBookDAO;
    private List<TypeBook> typeBookList;
    private EditText edMaTheLoaiDetail, edTenTheLoaiDetail, edMoTaDetail, edViTriDetail;
    private Button btnEditType,btnCancel;


    public TypeBookApdapter(Context context, List<TypeBook> typeBookList) {
        this.context = context;
        this.typeBookList = typeBookList;
    }

    @NonNull
    @Override
    public TypeBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_typebook, parent, false);

        TypeBookHolder typeBookHolder = new TypeBookHolder(view);
        return typeBookHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TypeBookHolder holder, final int position) {
        typeBookDAO = new TypeBookDAO(context);
        holder.tvText1.setText("Ma the loai: " + typeBookList.get(position).getMatheloaitype());
        holder.tvText2.setText("tentheloai : " + typeBookList.get(position).getTentheloai());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean result = typeBookDAO.isDelete(typeBookList.get(position).getMatheloaitype());
                if (result) {
                    typeBookList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setView(R.layout.typebook_detail);

                final AlertDialog alertDialog1 = alertDialog.show();
                edMaTheLoaiDetail = alertDialog1.findViewById(R.id.edMaTheLoaiDetail);
                edMaTheLoaiDetail.setText(typeBookList.get(position).getMatheloaitype());
                edTenTheLoaiDetail = alertDialog1.findViewById(R.id.edTenTheLoaiDetail);
                edTenTheLoaiDetail.setText(typeBookList.get(position).getTentheloai());
                edMoTaDetail = alertDialog1.findViewById(R.id.edMoTaDetail);
                edMoTaDetail.setText(typeBookList.get(position).getMota());
                edViTriDetail = alertDialog1.findViewById(R.id.edViTriDetail);
                edViTriDetail.setText(typeBookList.get(position).getVitri());

                btnEditType=alertDialog1.findViewById(R.id.btnEditTypeBook);
                btnCancel=alertDialog1.findViewById(R.id.btnCancel);

                btnEditType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String newMaTheLoai=edMaTheLoaiDetail.getText().toString();
                        String newTenTheLoai=edTenTheLoaiDetail.getText().toString();
                        String newMoTa=edMoTaDetail.getText().toString();
                        String newViTri=edViTriDetail.getText().toString();

                        if (newMaTheLoai.equals(" ")){
                            edMaTheLoaiDetail.setError("Bạn cần nhập tên");
                            edMaTheLoaiDetail.requestFocus();
                            return;
                        }else if (newTenTheLoai.equals(" ")){
                            edTenTheLoaiDetail.setError("Bạn cần nhập tên");
                            edTenTheLoaiDetail.requestFocus();
                            return;
                        }else if (newMoTa.equals(" ")){
                            edMoTaDetail.setError("Bạn cần nhập tên");
                            edMoTaDetail.requestFocus();
                            return;
                        }else if (newViTri.equals(" ")){
                            edViTriDetail.setError("Bạn cần nhập tên");
                            edViTriDetail.requestFocus();
                            return;
                        }else {
                            TypeBook typeBook=new TypeBook();
                            typeBook.setMatheloaitype(newMaTheLoai);
                            typeBook.setTentheloai(newTenTheLoai);
                            typeBook.setMota(newMoTa);
                            typeBook.setVitri(newViTri);
                            boolean result=typeBookDAO.updateTypeBook(typeBook);

                            if (result){
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                typeBookList = typeBookDAO.selectTypeBook();
                                typeBookApdapter =new TypeBookApdapter(context,typeBookList);
                                notifyDataSetChanged();
                                alertDialog1.dismiss();
                            }
                            else {
                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return typeBookList.size();
    }
}
