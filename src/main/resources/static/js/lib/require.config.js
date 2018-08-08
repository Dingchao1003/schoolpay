require.config({
    baseUrl:'/js/lib',
    paths : {
        "jquery" : "jquery/jquery-3.1.1.min",
         "bootstrap" : "bootstrap/bootstrap.min",
        "inspinia":"menu/inspinia",
        "menu":"menu/jquery.metisMenu",
        "slimscroll":"menu/jquery.slimscroll.min"

    },
    shim:{
        'jquery':{
            deps:[],
            exports:"$"
        },
        'bootstrap':{
            deps:['jquery']
        },
        'inspinia':{
            deps:['jquery']
        },
        'menu':{
            deps:['jquery']
        },
        'slimscroll':{
            deps:['jquery']
        }

    }
})






