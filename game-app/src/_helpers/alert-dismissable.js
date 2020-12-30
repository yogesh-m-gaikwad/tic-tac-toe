import React, { useState } from 'react';
import Alert from 'react-bootstrap/Alert';

export function AlertDismissible(props) {
    const [show, setShow] = useState(true);
    return (
       <Alert show={show} variant="danger" onClose={() => setShow(false)} dismissible>
             {props.errorMessage}
       </Alert>
    );
}