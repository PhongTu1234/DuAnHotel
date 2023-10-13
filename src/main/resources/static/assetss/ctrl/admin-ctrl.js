app = angular.module("admin-app", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/account", {
            templateUrl: "/admin/Users/index.html",
            controller: "account-ctrl"
        })
        .when("/Hotel", {
            templateUrl: "/admin/Hotel/index.html",
            controller: "product-ctrl"
        })
        .when("/HotelType", {
            templateUrl: "/admin/HotelType/index.html",
            controller: "category-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "/admin/authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .when("/room", {
            templateUrl: "/admin/Rooms/index.html",
            controller: "room-ctrl"
        })
        .otherwise({
            templateUrl: "/admin/dashboard.html"
        });
});