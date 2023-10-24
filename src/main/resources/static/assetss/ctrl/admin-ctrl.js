app = angular.module("admin-app", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/authority", {
            templateUrl: "/admin/Authority/index.html",
            controller: "authority-ctrl"
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
        .when("/account", {
            templateUrl: "/admin/Users/index.html",
            controller: "account-ctrl"
        })
        .when("/place", {
            templateUrl: "/admin/Place/index.html",
            controller: "place-ctrl"
        })
        .when("/roles", {
            templateUrl: "/admin/Roles/index.html",
            controller: "roles-ctrl"
        })
        .otherwise({
            templateUrl: "/admin/dashboard.html"
        });
});