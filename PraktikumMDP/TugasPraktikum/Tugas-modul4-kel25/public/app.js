if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('/sw.js')
        .then(function () {
            console.log('SW registered');
        });
}

new Vue({
    el :'#app',
    data:{
        kodepos : [],
        search : '',
        awal : 0,
        akhir : 15,
        halaman : 1,
        aboutpage : 1,
        lihat:true,
        tampil:true
    },
    created(){
        this.getData();
        console.log(this.awal)
    },
    methods:{
        next(){
            this.awal = this.awal+=15
            this.akhir = this.akhir+=15
            this.halaman = this.halaman+=1
        },
        prev() {
            this.awal = this.awal -= 15
            this.akhir = this.akhir -= 15
            this.halaman = this.halaman -= 1
        },

        getData(){
            var kd = this;
            axios.get('https://api.myjson.com/bins/r7shi').then(function(response){
                kd.kodepos = response.data;
            })
        }
    },
    computed:{
        cari(){
            return this.kodepos.filter(pos =>{
                return pos.kecamatan.toLowerCase().match(this.search.toLowerCase());
            });
        }
    }
    
});


    
