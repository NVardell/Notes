// src/components/Modal/Modal.js
// https://blog.logrocket.com/build-modal-with-react-portals/#closing-modal-with-escape-key

import { useEffect } from "react";
import "./modalStyles.css";

function Modal({ children, isOpen, handleClose }) {
  useEffect(() => {
    const closeOnEscapeKey = e => e.key === "Escape" ? handleClose() : null;
    document.body.addEventListener("keydown", closeOnEscapeKey);
    return () => {
      document.body.removeEventListener("keydown", closeOnEscapeKey);
    };
  }, [handleClose]);

  if (!isOpen) return null;

    return (
    <div className="modal">
      <button onClick={handleClose} className="close-btn">
        Close
      </button>
      <div className="modal-content">{children}</div>
    </div>
  );
};

export default Modal;

};

export default Modal;
