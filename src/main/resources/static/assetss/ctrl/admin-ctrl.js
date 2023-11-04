app = angular.module("admin-app", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
    	.when("/dash", {
		    templateUrl: "/admin/dashboard",
		    controller: "account-ctrl"
		})
        .when("/authority", {
            templateUrl: "/admin/Authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/blog", {
            templateUrl: "/admin/Blog/index.html",
            controller: "blog-ctrl"
        })
        .when("/booking", {
            templateUrl: "/admin/Booking/index.html",
            controller: "booking-ctrl"
        })
        .when("/bookingroom", {
            templateUrl: "/admin/BookingRoom/index.html",
            controller: "bookingroom-ctrl"
        })
        .when("/feedback", {
            templateUrl: "/admin/Feedback/index.html",
            controller: "feedback-ctrl"
        })
        .when("/Hotel", {
            templateUrl: "/admin/Hotel/index.html",
            controller: "product-ctrl"
        })
        .when("/HotelType", {
            templateUrl: "/admin/HotelType/index.html",
            controller: "category-ctrl"
        })
        .when("/images", {
            templateUrl: "/admin/Images/index.html",
            controller: "images-ctrl"
        })
        .when("/imageshotel", {
            templateUrl: "/admin/ImagesHotel/index.html",
            controller: "imageshotel-ctrl"
        })
        .when("/imagesroom", {
            templateUrl: "/admin/ImagesRoom/index.html",
            controller: "imagesroom-ctrl"
        })
        .when("/payment", {
            templateUrl: "/admin/Payment/index.html",
            controller: "payment-ctrl"
        })
        .when("/place", {
            templateUrl: "/admin/Place/index.html",
            controller: "place-ctrl"
        })
        .when("/roles", {
            templateUrl: "/admin/Roles/index.html",
            controller: "roles-ctrl"
        })
        .when("/room", {
            templateUrl: "/admin/Rooms/index.html",
            controller: "room-ctrl"
        })
        .when("/roomtype", {
            templateUrl: "/admin/RoomType/index.html",
            controller: "roomtype-ctrl"
        })
        .when("/service", {
            templateUrl: "/admin/Services/index.html",
            controller: "service-ctrl"
        })
        .when("/serviceroom", {
            templateUrl: "/admin/ServiceRoom/index.html",
            controller: "serviceroom-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/admin/authority/index.html",
            controller: "authority-ctrl"
        })
        .when("/unauthorized", {
            templateUrl: "/admin/authority/unauthorized.html",
            controller: "authority-ctrl"
        })
        .otherwise({
            templateUrl: "/admin/dashboard.html"
        });
});