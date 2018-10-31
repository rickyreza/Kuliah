using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using Kelompok25.Model;
using SQLite;
using Xamarin.Forms;

namespace Kelompok25.View
{
    public class HalamanEditData : ContentPage
    {
        private ListView _listView;
        private Entry _id;
        private Entry _nama;
        private Entry _jurusan;
        private Button _update;

        DataMahasiswa _datamahasiswa = new DataMahasiswa();

        string _dbPath = Path.Combine(System.Environment.GetFolderPath(System.Environment.SpecialFolder.Personal), "myDB.db4");

        public HalamanEditData()
        {
            this.Title = "Edit Data Mahasiswa";

            var db = new SQLiteConnection(_dbPath);

            StackLayout stackLayout = new StackLayout();

            _listView = new ListView();
            _listView.ItemsSource = db.Table<DataMahasiswa>().OrderBy(x => x.Nama).ToList();
            _listView.ItemSelected += _listView_ItemSelected;
            stackLayout.Children.Add(_listView);

            _id = new Entry();
            _id.Keyboard = Keyboard.Text;
            _id.Placeholder = "ID";
            _id.IsVisible = false;
            stackLayout.Children.Add(_id);

            _nama = new Entry();
            _nama.Keyboard = Keyboard.Text;
            _nama.Placeholder = "Nama Mahasiswa";
            stackLayout.Children.Add(_nama);

            _jurusan = new Entry();
            _jurusan.Keyboard = Keyboard.Text;
            _jurusan.Placeholder = "Jurusan";
            stackLayout.Children.Add(_jurusan);

            _update = new Button();
            _update.Text = "Update";
            _update.Clicked += _update_Clicked;
            stackLayout.Children.Add(_update);

            Content = stackLayout;

        }

        private async void _update_Clicked(object sender, EventArgs e)
        {
            var db = new SQLiteConnection(_dbPath);
            DataMahasiswa _dataMahasiswa = new DataMahasiswa()
            {
                Id = Convert.ToInt32(_id.Text),
                Nama = _nama.Text,
                Jurusan = _jurusan.Text
            };
            db.Update(_dataMahasiswa);
            await DisplayAlert(null, "Data " + _dataMahasiswa.Nama + " Berhasil Diupdate", "Ok");
            await Navigation.PopAsync();
        }

        private void _listView_ItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            _datamahasiswa = (DataMahasiswa)e.SelectedItem;
            _id.Text = _datamahasiswa.Id.ToString();
            _nama.Text = _datamahasiswa.Nama;
            _jurusan.Text = _datamahasiswa.Jurusan;
        }
    }
}