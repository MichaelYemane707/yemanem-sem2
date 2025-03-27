<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Add New Event</title>
    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- Font Awesome -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
      rel="stylesheet"
    />
    <link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
    <style>
      .form-container {
        max-width: 800px;
        margin: 40px auto;
        padding: 20px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        background-color: #fff;
      }
      body {
        background-color: #f8f9fa;
        padding: 20px;
      }
      .header-icon {
        color: #0d6efd;
        margin-right: 10px;
      }
    </style>
  </head>
  <body>
    <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
      <symbol id="bootstrap" viewBox="0 0 118 94">
        <title>Bootstrap</title>
        <path
          fill-rule="evenodd"
          clip-rule="evenodd"
          d="M24.509 0c-6.733 0-11.715 5.893-11.492 12.284.214 6.14-.064 14.092-2.066 20.577C8.943 39.365 5.547 43.485 0 44.014v5.972c5.547.529 8.943 4.649 10.951 11.153 2.002 6.485 2.28 14.437 2.066 20.577C12.794 88.106 17.776 94 24.51 94H93.5c6.733 0 11.714-5.893 11.491-12.284-.214-6.14.064-14.092 2.066-20.577 2.009-6.504 5.396-10.624 10.943-11.153v-5.972c-5.547-.529-8.934-4.649-10.943-11.153-2.002-6.484-2.28-14.437-2.066-20.577C105.214 5.894 100.233 0 93.5 0H24.508zM80 57.863C80 66.663 73.436 72 62.543 72H44a2 2 0 01-2-2V24a2 2 0 012-2h18.437c9.083 0 15.044 4.92 15.044 12.474 0 5.302-4.01 10.049-9.119 10.88v.277C75.317 46.394 80 51.21 80 57.863zM60.521 28.34H49.948v14.934h8.905c6.884 0 10.68-2.772 10.68-7.727 0-4.643-3.264-7.207-9.012-7.207zM49.948 49.2v16.458H60.91c7.167 0 10.964-2.876 10.964-8.281 0-5.406-3.903-8.178-11.425-8.178H49.948z"
        ></path>
      </symbol>
      <symbol id="home" viewBox="0 0 16 16">
        <path
          d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"
        />
      </symbol>
      <symbol id="speedometer2" viewBox="0 0 16 16">
        <path
          d="M8 4a.5.5 0 0 1 .5.5V6a.5.5 0 0 1-1 0V4.5A.5.5 0 0 1 8 4zM3.732 5.732a.5.5 0 0 1 .707 0l.915.914a.5.5 0 1 1-.708.708l-.914-.915a.5.5 0 0 1 0-.707zM2 10a.5.5 0 0 1 .5-.5h1.586a.5.5 0 0 1 0 1H2.5A.5.5 0 0 1 2 10zm9.5 0a.5.5 0 0 1 .5-.5h1.5a.5.5 0 0 1 0 1H12a.5.5 0 0 1-.5-.5zm.754-4.246a.389.389 0 0 0-.527-.02L7.547 9.31a.91.91 0 1 0 1.302 1.258l3.434-4.297a.389.389 0 0 0-.029-.518z"
        />
        <path
          fill-rule="evenodd"
          d="M0 10a8 8 0 1 1 15.547 2.661c-.442 1.253-1.845 1.602-2.932 1.25C11.309 13.488 9.475 13 8 13c-1.474 0-3.31.488-4.615.911-1.087.352-2.49.003-2.932-1.25A7.988 7.988 0 0 1 0 10zm8-7a7 7 0 0 0-6.603 9.329c.203.575.923.876 1.68.63C4.397 12.533 6.358 12 8 12s3.604.532 4.923.96c.757.245 1.477-.056 1.68-.631A7 7 0 0 0 8 3z"
        />
      </symbol>
      <symbol id="table" viewBox="0 0 16 16">
        <path
          d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm15 2h-4v3h4V4zm0 4h-4v3h4V8zm0 4h-4v3h3a1 1 0 0 0 1-1v-2zm-5 3v-3H6v3h4zm-5 0v-3H1v2a1 1 0 0 0 1 1h3zm-4-4h4V8H1v3zm0-4h4V4H1v3zm5-3v3h4V4H6zm4 4H6v3h4V8z"
        />
      </symbol>
      <symbol id="people-circle" viewBox="0 0 16 16">
        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
        <path
          fill-rule="evenodd"
          d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"
        />
      </symbol>
      <symbol id="grid" viewBox="0 0 16 16">
        <path
          d="M1 2.5A1.5 1.5 0 0 1 2.5 1h3A1.5 1.5 0 0 1 7 2.5v3A1.5 1.5 0 0 1 5.5 7h-3A1.5 1.5 0 0 1 1 5.5v-3zM2.5 2a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 1h3A1.5 1.5 0 0 1 15 2.5v3A1.5 1.5 0 0 1 13.5 7h-3A1.5 1.5 0 0 1 9 5.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zM1 10.5A1.5 1.5 0 0 1 2.5 9h3A1.5 1.5 0 0 1 7 10.5v3A1.5 1.5 0 0 1 5.5 15h-3A1.5 1.5 0 0 1 1 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3zm6.5.5A1.5 1.5 0 0 1 10.5 9h3a1.5 1.5 0 0 1 1.5 1.5v3a1.5 1.5 0 0 1-1.5 1.5h-3A1.5 1.5 0 0 1 9 13.5v-3zm1.5-.5a.5.5 0 0 0-.5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 0-.5-.5h-3z"
        />
      </symbol>
    </svg>
    <div class="container">
      <header
        class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom"
      >
        <a
          href="/"
          class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none"
        >
          <svg class="bi me-2" width="40" height="32">
            <use xlink:href="#bootstrap" />
          </svg>
          <span class="fs-4">Mikky Event MGMT</span>
        </a>

        <ul class="nav nav-pills">
          <li class="nav-item">
            <a
              href="/emmas/index.jsp"
              class="nav-link active"
              aria-current="page"
              >Home</a
            >
          </li>
          <li class="nav-item">
            <a href="/emmas/addEvent.jsp" class="nav-link">Create Event</a>
          </li>
        </ul>
      </header>
    </div>
    <div class="container">
      <div class="form-container">
        <div class="text-center mb-4">
          <h1 class="display-5">
            <i class="fas fa-calendar-plus header-icon"></i>
            Create a New Event
          </h1>
          <p class="text-muted">
            Fill in the details below to create your event
          </p>
        </div>

        <form
          action="addEvent"
          method="POST"
          class="needs-validation"
          novalidate
        >
          <div class="row g-3">
            <div class="col-12 mb-3">
              <label for="name" class="form-label">Event Name</label>
              <div class="input-group">
                <span class="input-group-text"
                  ><i class="fas fa-signature"></i
                ></span>
                <input
                  type="text"
                  class="form-control"
                  id="name"
                  name="name"
                  required
                  placeholder="Enter event name"
                />
              </div>
            </div>

            <div class="col-md-6 mb-3">
              <label for="eventType" class="form-label">Event Type</label>
              <div class="input-group">
                <span class="input-group-text"><i class="fas fa-tag"></i></span>
                <select
                  class="form-select"
                  id="eventType"
                  name="eventType"
                  required
                >
                  <option value="">Choose event type...</option>
                  <option value="Conference">Conference</option>
                  <option value="Wedding">Wedding</option>
                  <option value="Workshop">Workshop</option>
                  <option value="Party">Party</option>
                </select>
              </div>
            </div>

            <div class="col-md-6 mb-3">
              <label for="date" class="form-label">Event Date</label>
              <div class="input-group">
                <span class="input-group-text"
                  ><i class="fas fa-calendar"></i
                ></span>
                <input
                  type="date"
                  class="form-control"
                  id="date"
                  name="date"
                  required
                />
              </div>
            </div>

            <div class="col-12 mb-3">
              <label for="location" class="form-label">Location</label>
              <div class="input-group">
                <span class="input-group-text"
                  ><i class="fas fa-map-marker-alt"></i
                ></span>
                <input
                  type="text"
                  class="form-control"
                  id="location"
                  name="location"
                  required
                  placeholder="Enter event location"
                />
              </div>
            </div>

            <div class="col-12 mb-4">
              <label for="description" class="form-label">Description</label>
              <div class="input-group">
                <span class="input-group-text"
                  ><i class="fas fa-align-left"></i
                ></span>
                <textarea
                  class="form-control"
                  id="description"
                  name="description"
                  rows="4"
                  required
                  placeholder="Describe your event..."
                ></textarea>
              </div>
            </div>
          </div>

          <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button
              type="button"
              class="btn btn-outline-secondary me-2"
              onclick="window.history.back()"
            >
              <i class="fas fa-times"></i> Cancel
            </button>
            <button type="submit" class="btn btn-primary">
              <i class="fas fa-plus"></i> Create Event
            </button>
          </div>
        </form>
      </div>
    </div>
    <div class="modal fade" id="successModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-success text-white">
            <h5 class="modal-title">Success!</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <p>Event has been created successfully!</p>
          </div>
          <div class="modal-footer">
            <a href="index.jsp" class="btn btn-primary">View All Events</a>
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Error Modal -->
    <div class="modal fade" id="errorModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-danger text-white">
            <h5 class="modal-title">Error</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <p id="errorMessage"></p>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Form validation script -->
    <script>
      (function () {
        "use strict";
        var forms = document.querySelectorAll(".needs-validation");
        Array.prototype.slice.call(forms).forEach(function (form) {
          form.addEventListener(
            "submit",
            function (event) {
              if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
              }
              form.classList.add("was-validated");
            },
            false
          );
        });
      })();

      document.querySelector("form").addEventListener("submit", function (e) {
        e.preventDefault();

        // Create URLSearchParams from form data
        const formData = new URLSearchParams(new FormData(this));

        fetch("addEvent", {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          body: formData.toString(),
        })
          .then((response) => response.json())
          .then((data) => {
            if (data.status === "success") {
              var successModal = new bootstrap.Modal(
                document.getElementById("successModal")
              );
              successModal.show();
            } else {
              document.getElementById("errorMessage").textContent =
                data.message;
              var errorModal = new bootstrap.Modal(
                document.getElementById("errorModal")
              );
              errorModal.show();
            }
          })
          .catch((error) => {
            console.error("Error:", error); // Add this for debugging
            document.getElementById("errorMessage").textContent =
              "An unexpected error occurred. Please try again.";
            var errorModal = new bootstrap.Modal(
              document.getElementById("errorModal")
            );
            errorModal.show();
          });
      });
    </script>
  </body>
</html>
