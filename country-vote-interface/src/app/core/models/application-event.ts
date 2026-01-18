import {EventTypeEnum} from '../enums';

export interface ApplicationEvent<T> {
  type: EventTypeEnum;
  payload: T;
}
