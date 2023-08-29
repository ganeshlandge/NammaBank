import { ReactNode } from "react";

export type RouteType = {
  element: ReactNode,
  onClick?: () => void,
  state: string,
  index?: boolean,
  path?: string,
  child?: RouteType[],
  sidebarProps?: {
    displayText: string,
    icon?: ReactNode;
  };
};