import React from "react";

const SideBar = () => {
  return (
    <div className="col-2 sidebar d-flex flex-column min-vh-100">
      <h3>My Apps</h3>
      <div className="list-group flex-grow-1">
        <button type="button" className="btn btn-primary btn-lg mt-3 ">app 1</button>
        <button type="button" className="btn btn-primary btn-lg mt-3">app 2</button>
        <button type="button" className="btn btn-primary btn-lg mt-3">app 3</button>
      </div>
    </div>
  );
};

export default SideBar;
