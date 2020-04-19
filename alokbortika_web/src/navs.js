export const navUNO = {
  items: [
    {
      name: "UNO",
      url: "/uno",
      icon: "icon-cursor",
      children: [
        {
          name: "ত্রাণ অনুরোধ",
          url: "/uno/reliefRequests",
          icon: "icon-drop",
        },
        {
          name: "ত্রাণ ব্যবস্থাপনা",
          url: "/uno/manageReliefs",
          icon: "icon-settings",
        },
        {
          name: "অভিযোগ",
          url: "/uno/reports",
          icon: "icon-chart",
        },
      ],
    },
  ],
};

export const navAdmin = {
  items: [
    {
      name: "ড্যাশবোর্ড",
      url: "/admin/dashboard",
      icon: "icon-chart",
    },
    {
      name: "ত্রাণ",
      url: "/admin/relief",
      icon: "icon-settings",
    },
  ],
};
