package com.example.lab2_mob204.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_mob204.R;
import com.example.lab2_mob204.dao.BookDAO;
import com.example.lab2_mob204.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookHloder> {
    private Context context;
    private BookDAO bookDAO;
    private List<Book> bookList;
    private BookAdapter bookAdapter;
    private EditText edMatheloai,edMasach,edtacgia,ednxb,edtieude,edsoluong,edgiabia;
    private Button btnEdit,btnCancel;


    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.rvbook,parent,false);
        BookHloder bookHloder=new BookHloder(view);

        return bookHloder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookHloder holder,final int position) {

        bookDAO=new BookDAO(context);
        holder.tvText1.setText("Ten Sach: "+bookList.get(position).getMasach());
        holder.tvText2.setText("So Luong: "+bookList.get(position).getSoluong());

        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result=bookDAO.isDelete(bookList.get(position).getMasach());

                if (result){
                    bookList.remove(position);
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

                alertDialog.setView(R.layout.book_detail);


                final AlertDialog alertDialog1 = alertDialog.show();
               edMasach= alertDialog1.findViewById(R.id.edMaSachDetail);
                edMasach.setText(bookList.get(position).getMasach());

                edMatheloai= alertDialog1.findViewById(R.id.edMaTheLoaiDetail);
                edMatheloai.setText(bookList.get(position).getMatheloai());

                edgiabia= alertDialog1.findViewById(R.id.edGiaBiaDetail);
                edgiabia.setText(bookList.get(position).getGiabia());

                ednxb= alertDialog1.findViewById(R.id.edNXBDetail);
                ednxb.setText(bookList.get(position).getNhaxuatban());

                edsoluong= alertDialog1.findViewById(R.id.edSoLuongDetail);
                edsoluong.setText(bookList.get(position).getSoluong());

                edtieude= alertDialog1.findViewById(R.id.edTenSachDetail);
                edtieude.setText(bookList.get(position).getTieude());

                edtacgia= alertDialog1.findViewById(R.id.edTacgiaDetail);
                edtacgia.setText(bookList.get(position).getTacgia());

                btnCancel=alertDialog1.findViewById(R.id.btnCancel);
                btnEdit=alertDialog1.findViewById(R.id.btnEditBook);

                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newMaSach=edMasach.getText().toString().trim();
                        String newMatheloai=edMatheloai.getText().toString().trim();
                        String newtieude=edtieude.getText().toString().trim();
                        String newTacgia=edtacgia.getText().toString().trim();
                        String newnhaxuatban=ednxb.getText().toString().trim();
                        String newsoluong=edsoluong.getText().toString().trim();
                        String newgiabia=edgiabia.getText().toString().trim();
                        if (edMasach.equals(" ")){
                            edMasach.setError("Ma sach khong the de trong");
                            edMasach.requestFocus();
                            return;
                        }else  if (edMatheloai.equals(" ")){
                            edMatheloai.setError("Ma The loai khong the de trong");
                            edMatheloai.requestFocus();
                            return;
                        }else  if (edgiabia.equals(" ")){
                            edgiabia.setError("Gia Bia khong the de trong");
                            edgiabia.requestFocus();
                            return;
                        }else  if (edsoluong.equals(" ")){
                            edsoluong.setError("So Luong khong the de trong");
                            edsoluong.requestFocus();
                            return;
                        }else  if (ednxb.equals(" ")){
                            ednxb.setError("NXB khong the de trong");
                            ednxb.requestFocus();
                            return;
                        }else  if (edtacgia.equals(" ")){
                            edtacgia.setError("Tac Gia khong the de trong");
                            edtacgia.requestFocus();
                            return;
                        }else  if (edtieude.equals(" ")){
                            edtieude.setError("Tieu De khong the de trong");
                            edtieude.requestFocus();
                            return;
                        }else {
                            Book book=new Book();
                            book.setMasach(newMaSach);
                            book.setSoluong(newsoluong);
                            book.setGiabia(newgiabia);
                            book.setNhaxuatban(newnhaxuatban);
                            book.setTacgia(newTacgia);
                            book.setTieude(newtieude);
                            book.setMatheloai(newMatheloai);
                            boolean result=bookDAO.updateBook(book);
                            if (result) {
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                bookList = bookDAO.selectBook();
                                bookAdapter =new BookAdapter(context,bookList);
                                notifyDataSetChanged();
                                alertDialog1.dismiss();
                            } else {
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
        return bookList.size();
    }
}
