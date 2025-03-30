"""librarymnt URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include

from libraryapp import views

urlpatterns = [
    path('',views.main,name="main"),
    # path('maincode',views.maincode,name="maincode"),
    path('login_action', views.login_action, name="login_action"),
    path('logout', views.logout, name="logout"),
    path('linklist',views.linklist, name="linklist"),
    path('home',views.home, name="home"),
    path('library',views.library,name="library"),
    path('complainttable', views.complainttable, name="complainttable"),
    path('reply/<int:id>',views.reply,name="reply"),
    path('notificationupdate',views.notificationupdate, name="notificationupdate"),
    path('sendnotification',views.sendnotification, name="sendnotification"),
    path('ratings',views.ratings,name="ratings"),
    path('blockunblock',views.blockunblock,name="blockunblock"),
    # path('feedback',views.feed_back,name="feedback"),



    path('accept/<int:id>',views.accept,name="accept"),
    path('reject/<int:id>', views.reject, name="reject"),
    path('block/<int:id>',views.block,name="block"),
    path('unblock/<int:id>', views.unblock, name="unblock"),



    path('librarysearch', views.librarysearch, name="librarysearch"),
    path('notificationsearch', views.notificationsearch, name="notificationsearch"),
    path('complaintsearch', views.complaintsearch, name="complaintsearch"),
    path('creply', views.creply, name="creply"),
    path('ratingsearch', views.ratingsearch, name="ratingssearch"),
    path('reportsearch', views.reportsearch, name="reportsearch"),
    # path('feedbacksearch', views.feedbacksearch, name="feedbacksearch"),
    path('addnotfy', views.addnotfy, name="addnotfy"),
    path('notdel/<int:id>', views.notdel, name="notdel"),




    path('android_logincode', views.android_logincode, name="android_logincode"),


    #library

    path('library_register', views.library_register, name="library_register"),
    path('view_chat', views.view_chat, name="view_chat"),
    path('view_chat_search', views.view_chat_search, name="view_chat_search"),
    path('lib_insert_chat', views.lib_insert_chat, name="lib_insert_chat"),
    path('view_message2', views.view_message2, name="view_message2"),
    path('view_book', views.view_book, name="view_book"),
    path('addbook', views.addbook, name="addbook"),
    path('view_book_search', views.view_book_search, name="view_book_search"),
    path('del_book', views.del_book, name="del_book"),
    path('view_order', views.view_order, name="view_order"),
    path('view_order_search', views.view_order_search, name="view_order_search"),
    path('view_library_events', views.view_library_events, name="view_library_events"),
    path('view_library_events_search', views.view_library_events_search, name="view_library_events_search"),
    path('view_admnotftn', views.view_admnotftn, name="view_admnotftn"),
    path('view_admnotftn_search', views.view_admnotftn_search, name="view_admnotftn_search"),
    path('view_donatn', views.view_donatn, name="view_donatn"),
    path('view_donatn_search', views.view_donatn_search, name="view_donatn_search"),
    path('view_suggestbk', views.view_suggestbk, name="view_suggestbk"),
    path('view_suggestbk_search', views.view_suggestbk_search, name="view_suggestbk_search"),
    path('orderbook', views.orderbook, name="orderbook"),
    path('ddtnotfy', views.ddtnotfy, name="ddtnotfy"),
    path('view_authboard', views.view_authboard, name="view_authboard"),
    path('view_authboard_search', views.view_authboard_search, name="view_authboard_search"),
    path('view_bookstaken', views.view_bookstaken, name="view_bookstaken"),
    path('view_bookstaken_search', views.view_bookstaken_search, name="view_bookstaken_search"),
    path('addevnt', views.addevnt, name="addevnt"),
    path('view_duedate', views.view_duedate, name="view_duedate"),
    path('view_duedate_search', views.view_duedate_search, name="view_duedate_search"),
    path('report_user', views.report_user, name="report_user"),
    path('extendduedate', views.extendduedate, name="extendduedate"),
    path('accept_writing', views.accept_writing, name="accept_writing"),
    path('rjct_writing', views.rjct_writing, name="rjct_writing"),
    path('accept_order', views.accept_order, name="accept_order"),
    path('reject_order', views.reject_order, name="reject_order"),
    path('return_book', views.return_book, name="return_book"),
    path('ordspin', views.ordspin, name="ordspin"),
    path('lib_more', views.lib_more, name="lib_more"),
    path('view_donatn_more', views.view_donatn_more, name="view_donatn_more"),
    path('view_suggestbk_more', views.view_suggestbk_more, name="view_suggestbk_more"),

    # user

    path('user_reg', views.user_reg, name="user_reg"),
    path('user_vlibraries', views.user_vlibraries, name="user_vlibraries"),
    path('user_libsearch', views.user_libsearch, name="user_libsearch"),
    path('user_vcustomlibry', views.user_vcustomlibry, name="user_vcustomlibry"),
    path('user_vcustomlibry_search', views.user_vcustomlibry_search, name="user_vcustomlibry_search"),
    path('user_vcomplaints', views.user_vcomplaints, name="user_vcomplaints"),
    path('user_vcomplaints_search', views.user_vcomplaints_search, name="user_vcomplaints_search"),
    path('user_vlibbooks', views.user_vlibbooks, name="user_vlibbooks"),
    path('user_vlibbooks_search', views.user_vlibbooks_search, name="user_vlibbooks_search"),
    path('user_vbooklog', views.user_vbooklog, name="user_vbooklog"),
    path('user_vbooklog_search', views.user_vbooklog_search, name="user_vbooklog_search"),
    path('user_libevnt', views.user_libevnt, name="user_libevnt"),
    path('user_libevnt_search', views.user_libevnt_search, name="user_libevnt_search"),
    path('user_vauthbrd', views.user_vauthbrd, name="user_vauthbrd"),
    path('user_vauthbrd_search', views.user_vauthbrd_search, name="user_vauthbrd_search"),
    path('user_vadmnotfy', views.user_vadmnotfy, name="user_vadmnotfy"),
    path('user_vadmnotfy_search', views.user_vadmnotfy_search, name="user_vadmnotfy_search"),
    path('user_vlibnotfy', views.user_vlibnotfy, name="user_vlibnotfy"),
    path('user_vlibnotfy_search', views.user_vlibnotfy_search, name="user_vlibnotfy_search"),
    path('user_sendcomplaint', views.user_sendcomplaint, name="user_sendcomplaint"),
    path('user_addwriting', views.user_addwriting, name="user_addwriting"),
    path('user_placeorder', views.user_placeorder, name="user_placeorder"),
    path('user_donate', views.user_donate, name="user_donate"),
    path('user_suggest', views.user_suggest, name="user_suggest"),
    path('user_addtocustomlby', views.user_addtocustomlby, name="user_addtocustomlby"),
    path('user_addratrvw', views.user_addratrvw, name="user_addratrvw"),
    path('addbook_fromdonations', views.addbook_fromdonations, name="addbook_fromdonations"),
    path('user_more', views.user_more, name="user_more"),
    path('del_ebook', views.del_ebook, name="del_ebook"),
    path('forgot_password', views.forgot_password, name="forgot_password"),
    path('placeorder_new',views.placeorder_new, name="placeorder_new"),
    path('placeorder_new_search',views.placeorder_new_search, name="placeorder_new_search"),

]

