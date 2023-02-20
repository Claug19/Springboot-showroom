$(document).ready(function () {

    $("#search-form").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });


    $("#search-form-cars").submit(function (event) {
        event.preventDefault();
        fire_ajax_cars_submit()
    });


    $("#search-form-cars-that-start-with").submit(function (event) {
        event.preventDefault();
        fire_ajax_car_that_start_with_submit()
    });


    $("#search-form-model").submit(function (event) {
        event.preventDefault();
        fire_ajax_search_model_submit()
    });


    $("#search-form-model-filtered-by-year").submit(function (event) {
        event.preventDefault();
        fire_ajax_search_model_filtered_by_year_submit()
    });


    $("#search-form-model-filtered-by-price").submit(function (event) {
        event.preventDefault();
        fire_ajax_search_model_filtered_by_price_submit()
    });


    $("#create-form-reserved-car").submit(function (event) {
        event.preventDefault();
        fire_ajax_create_reserved_car_submit()
    });


    $("#delete-form-reserved-car").submit(function (event) {
        event.preventDefault();
        fire_ajax_delete_reserved_car_submit()
    });


    $("#search-form-user-wsdl").submit(function (event) {
        event.preventDefault();
        fire_wsdl_user_submit()
    });
});


function fire_ajax_submit() {

    var search = {}
    search["username"] = $("#username").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });
}

function fire_ajax_cars_submit() {

    var search = {}
    search["carBrand"] = $("#carBrand").val();

    $("#btn-search-car").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search-car",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search-car").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search-car").prop("disabled", false);

        }
    });
}

function fire_ajax_car_that_start_with_submit() {

    var search = {}
    search["carBrandThatStartWith"] = $("#carBrandThatStartWith").val();

    $("#bth-search-car-that-start-with").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search-car-that-start-with",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#bth-search-car-that-start-with").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#bth-search-car-that-start-with").prop("disabled", false);

        }
    });
}

function fire_ajax_search_model_submit() {

    var search = {}
    search["carModel"] = $("#carModel").val();

    $("#bth-search-model").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search-model",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#bth-search-model").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#bth-search-model").prop("disabled", false);

        }
    });
}

function fire_ajax_search_model_filtered_by_year_submit() {

    var search = {}
    search["carModelFilteredByYear"] = $("#carModelFilteredByYear").val();

    $("#bth-search-model-filtered-by-year").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search-model-filtered-by-year",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#bth-search-model-filtered-by-year").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#bth-search-model-filtered-by-year").prop("disabled", false);

        }
    });
}

function fire_ajax_search_model_filtered_by_price_submit() {

    var search = {}
    search["carModelFilteredByPrice"] = $("#carModelFilteredByPrice").val();

    $("#bth-search-model-filtered-by-price").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search-model-filtered-by-price",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#bth-search-model-filtered-by-price").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#bth-search-model-filtered-by-price").prop("disabled", false);

        }
    });
}

function fire_ajax_create_reserved_car_submit() {

    var search = {}
    search["reservedCar"] = $("#reservedCar").val();

    $("#bth-create-reserved-car").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/add-reserved-car",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#bth-create-reserved-car").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#bth-create-reserved-car").prop("disabled", false);

        }
    });
}

function fire_ajax_delete_booked_book_submit() {

    var search = {}
    search["deletedReservedCar"] = $("#deletedReservedCar").val();

    $("#bth-delete-reserved-car").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/delete-reserved-car",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#bth-delete-reserved-car").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#bth-delete-reserved-car").prop("disabled", false);

        }
    });
}

function fire_wsdl_user_submit() {

    var search = {}
    search["userWSDL"] = $("#userWSDL").val();

    $("#btn-search-user-wsdl").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search-user-wsdl",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search-user-wsdl").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search-user-wsdl").prop("disabled", false);

        }
    });
}
