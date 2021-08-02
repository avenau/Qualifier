import { NotificationManager} from 'react-notifications';

const Notification = (props) => {
    switch (props.type) 
    {
        case 'info':
            NotificationManager.info(props.msg);
            break;
        case 'success':
            NotificationManager.success(props.msg, props.title);
            break;
        case 'warning':
            NotificationManager.warning(props.msg, props.title, 3000);
            break;
        case 'error':
            NotificationManager.error(props.msg, props.title, 5000);
            break;
        default:
            break;
    }
}

export default Notification;